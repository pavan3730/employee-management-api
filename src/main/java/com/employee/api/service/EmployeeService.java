package com.employee.api.service;

import com.employee.api.exception.EmployeeNotFoundException;
import com.employee.api.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService{

    private final List<Employee> employees = new ArrayList<>();
    private long nextId = 1;

    public Employee createEmployee(Employee employee) {
        employee.setId(nextId);
        nextId++;

        employees.add(employee);

        return employee;
    }

    public List<Employee> getAllEmployees(){
        return employees;
    }

    public Employee getEmployeeById(Long id){
        for (Employee employee : employees){
            if(employee.getId().equals(id)){
                return employee;
            }
        }
        throw new EmployeeNotFoundException(
                "Employee not found with ID: " + id
        );
    }

    public Employee updateEmployeeById(
            Long id,
            Employee updatedEmployee) {

        Employee employee = getEmployeeById(id);

        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setDepartment(updatedEmployee.getDepartment());

        return employee;
    }

    public void deleteEmployeeById(Long id) {
        Employee employeeToDelete = getEmployeeById(id);
        employees.remove(employeeToDelete);
    }

    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> matchingEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getName().toLowerCase()
                    .contains(name.toLowerCase())) {
                matchingEmployees.add(employee);
            }
        }
        return matchingEmployees;
    }
}
