package com.employee.api.controller;

import com.employee.api.dto.EmployeePatchRequest;
import com.employee.api.dto.EmployeeRequest;
import com.employee.api.dto.EmployeeResponse;
import com.employee.api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private long nextId = 1;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        EmployeeResponse createdEmployee =
                employeeService.createEmployee(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdEmployee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id){
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployeeById(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        EmployeeResponse updatedEmployee =
                employeeService.updateEmployeeById(id, request);

        return ResponseEntity.ok(updatedEmployee);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponse> partiallyUpdateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeePatchRequest request) {

        EmployeeResponse updatedEmployee =
                employeeService.partiallyUpdateEmployee(id, request);

        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(
            @PathVariable Long id) {

        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponse>> searchEmployeesByName(
            @RequestParam String name) {

        List<EmployeeResponse> employees =
                employeeService.searchEmployeesByName(name);

        return ResponseEntity.ok(employees);
    }

}
