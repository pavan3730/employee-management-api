package com.employee.api.service;

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
        return null;
    }

    public Employee updateEmployeeById(Long id, Employee updatedEmployee){
        for (Employee employee : employees){
            if (employee.getId().equals(id)){
                employee.setName(updatedEmployee.getName());
                employee.setEmail(updatedEmployee.getEmail());
                employee.setDepartment(updatedEmployee.getDepartment());
                return employee;
            }
        }
        return null;
    }

    public boolean deleteEmployeeById(Long id) {
        Employee employeeToDelete = getEmployeeById(id);

        if (employeeToDelete == null) {
            return false;
        }
        employees.remove(employeeToDelete);
        return true;
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
