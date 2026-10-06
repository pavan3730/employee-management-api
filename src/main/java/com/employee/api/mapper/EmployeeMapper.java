package com.employee.api.mapper;

import com.employee.api.dto.EmployeeRequest;
import com.employee.api.dto.EmployeeResponse;
import com.employee.api.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());

        return employee;
    }

    public EmployeeResponse toResponse(Employee employee) {

        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment()
        );
    }

    public List<EmployeeResponse> toResponseList(
            List<Employee> employees) {

        List<EmployeeResponse> responses = new ArrayList<>();

        for (Employee employee : employees) {
            responses.add(toResponse(employee));
        }

        return responses;
    }

    public void updateEntity(
            EmployeeRequest request,
            Employee employee) {

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
    }
}