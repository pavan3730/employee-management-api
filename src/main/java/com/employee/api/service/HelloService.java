package com.employee.api.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getGreeting() {
        return "Hello from Employee Management API!";
    }
}
