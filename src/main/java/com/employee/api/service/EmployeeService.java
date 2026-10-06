package com.employee.api.service;

import com.employee.api.dto.EmployeeRequest;
import com.employee.api.dto.EmployeeResponse;
import com.employee.api.entity.Employee;
import com.employee.api.exception.DuplicateEmployeeEmailException;
import com.employee.api.exception.EmployeeNotFoundException;
import com.employee.api.mapper.EmployeeMapper;
import com.employee.api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {

        Optional<Employee> existingEmployee = employeeRepository.findByEmailIgnoreCase(request.getEmail());
        if (existingEmployee.isPresent()){
            throw new DuplicateEmployeeEmailException(
                    "An employee with this email already exists"
            );
        }
        Employee employee = employeeMapper.toEntity(request);

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(savedEmployee);
    }

    public List<EmployeeResponse> getAllEmployees(){

        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toResponseList(employees);
    }

    private Employee findEmployeeEntityById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with ID: " + id
                ));
    }

    public EmployeeResponse getEmployeeById(Long id) {

        Employee employee = findEmployeeEntityById(id);

        return employeeMapper.toResponse(employee);
    }

    public EmployeeResponse updateEmployeeById(
            Long id,
            EmployeeRequest request) {

        Employee existingEmployee =
                findEmployeeEntityById(id);

        Optional<Employee> employeeWithEmail =
                employeeRepository.findByEmailIgnoreCase(
                        request.getEmail()
                );

        if (employeeWithEmail.isPresent()
                && !employeeWithEmail.get().getId().equals(id)) {

            throw new DuplicateEmployeeEmailException(
                    "An employee with this email already exists"
            );
        }

        employeeMapper.updateEntity(request, existingEmployee);

        Employee savedEmployee =
                employeeRepository.save(existingEmployee);

        return employeeMapper.toResponse(savedEmployee);
    }
    public void deleteEmployeeById(Long id) {

        Employee employee = findEmployeeEntityById(id);

        employeeRepository.delete(employee);
    }

    public List<EmployeeResponse> searchEmployeesByName(String name) {
        List<Employee> employees =
                employeeRepository.findByNameContainingIgnoreCase(name);

        return employeeMapper.toResponseList(employees);
    }
}
