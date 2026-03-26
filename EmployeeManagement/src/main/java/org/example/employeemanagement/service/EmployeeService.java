package org.example.employeemanagement.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.employeemanagement.dao.EmployeeDAO;
import org.example.employeemanagement.domain.Employee;
import org.example.employeemanagement.dto.EmployeeRequestDTO;
import org.example.employeemanagement.dto.EmployeeResponseDTO;
import org.example.employeemanagement.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public EmployeeResponseDTO getEmployeeById(Long id) {
        log.info("Fetching employee by id {}", id);
        Employee employee = employeeDAO.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return mapToResponseDTO(employee);
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeDAO.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmployeeResponseDTO createEmployee(@Valid @RequestBody EmployeeRequestDTO requestDTO) {
        log.info("Creating employee with email: {}", requestDTO.getEmail());

        if (employeeDAO.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Employee employee = mapToEntity(requestDTO);

        employee.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        Employee savedEmployee = employeeDAO.save(employee);
        log.info("Successfully created employee with ID: {}", savedEmployee.getId());

        return mapToResponseDTO(savedEmployee);
    }

    @Transactional
    public EmployeeResponseDTO updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        log.info("Updating employee with id: {}", id);

        Employee employee = employeeDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setFirstName(employeeRequestDTO.getFirstName());
        employee.setMiddleName(employeeRequestDTO.getMiddleName());
        employee.setLastName(employeeRequestDTO.getLastName());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setDepartment(employeeRequestDTO.getDepartment());
        employee.setJobTitle(employeeRequestDTO.getJobTitle());
        employee.setSalary(employeeRequestDTO.getSalary());

        employee.setPassword(passwordEncoder.encode(employeeRequestDTO.getPassword()));

        Employee updatedEmployee = employeeDAO.save(employee);

        return mapToResponseDTO(updatedEmployee);
    }

    @Transactional
    public void deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with id: {}", id);

        Employee employee = employeeDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employeeDAO.deleteById(id);
    }

    private EmployeeResponseDTO mapToResponseDTO(Employee employee) {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .middleName(employee.getMiddleName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .jobTitle(employee.getJobTitle())
                .salary(employee.getSalary())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }

    private Employee mapToEntity(EmployeeRequestDTO employeeRequestDTO) {
        return Employee.builder()
                .firstName(employeeRequestDTO.getFirstName())
                .middleName(employeeRequestDTO.getMiddleName())
                .lastName(employeeRequestDTO.getLastName())
                .email(employeeRequestDTO.getEmail())
                .department(employeeRequestDTO.getDepartment())
                .jobTitle(employeeRequestDTO.getJobTitle())
                .salary(employeeRequestDTO.getSalary())
                .build();
    }
}
