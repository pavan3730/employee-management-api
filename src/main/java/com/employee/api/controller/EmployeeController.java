package com.employee.api.controller;

import com.employee.api.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>();
    private long nextId = 1;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @RequestBody Employee employee
    ) {
        employee.setId(nextId);
        nextId++;

        employees.add(employee);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        for(Employee employee: employees){
            if(employee.getId().equals(id)){
                return ResponseEntity.ok(employee);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee updatedemployee){
        for(Employee employee: employees){
            if(employee.getId().equals(id)){
                employee.setName(updatedemployee.getName());
                employee.setEmail(updatedemployee.getEmail());
                employee.setDepartment(updatedemployee.getDepartment());
                return ResponseEntity.ok(employee);
            }
        }
        return ResponseEntity.notFound().build();
    }

}
