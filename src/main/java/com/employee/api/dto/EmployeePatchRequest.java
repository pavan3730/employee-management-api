package com.employee.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class EmployeePatchRequest {
    @Pattern(
            regexp = ".*\\S.*",
            message = "Name must not be blank"
    )
    private String name;
    @Pattern(
            regexp = ".*\\S.*",
            message = "Email must not be blank"
    )
    @Email(message = "Email must be valid")
    private String email;

    @Pattern(
            regexp = ".*\\S.*",
            message = "Department must not be blank"
    )
    private String department;
    public EmployeePatchRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
