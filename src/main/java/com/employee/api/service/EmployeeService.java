package com.employee.api.service;

import com.employee.api.exception.DuplicateEmployeeEmailException;
import com.employee.api.exception.EmployeeNotFoundException;
import com.employee.api.entity.Employee;
import com.employee.api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService{

    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {

        Optional<Employee> existingEmployee = employeeRepository.findByEmailIgnoreCase(employee.getEmail());
        if (existingEmployee.isPresent()){
            throw new DuplicateEmployeeEmailException(
                    "An employee with this email already exists"
            );
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent()){
            return employee.get();
        }
        throw new EmployeeNotFoundException(
                "Employee not found with ID: " + id
        );
    }

    public Employee updateEmployeeById(
            Long id,
            Employee updatedEmployee) {

        Employee existingEmployee = getEmployeeById(id);

        Optional<Employee> employeeWithEmail =
                employeeRepository.findByEmailIgnoreCase(
                        updatedEmployee.getEmail()
                );

        if (employeeWithEmail.isPresent()
                && !employeeWithEmail.get().getId().equals(id)) {

            throw new DuplicateEmployeeEmailException(
                    "An employee with this email already exists"
            );
        }

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());


        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployeeById(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }
}
