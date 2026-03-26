package org.example.employeemanagement.exception;

import org.example.employeemanagement.dao.EmployeeDAO;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
