package com.employee.api.controller;

import com.employee.api.model.Employee;
import com.employee.api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final List<Employee> employees = new ArrayList<>();
    private long nextId = 1;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @Valid @RequestBody Employee employee
    ) {
        Employee createdEmployee =
                employeeService.createEmployee(employee);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeService.getEmployeeById(id);
        if(employee != null){
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @Valid @RequestBody Employee updatedEmployee){
        Employee employee = employeeService.updateEmployeeById(id,updatedEmployee);
        if(employee != null){
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(
            @PathVariable Long id) {

        boolean deleted = employeeService.deleteEmployeeById(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployeesByName(
            @RequestParam String name) {

        List<Employee> searchEmployeeByName = employeeService.searchEmployeesByName(name);

        return ResponseEntity.ok(searchEmployeeByName);
    }

}
