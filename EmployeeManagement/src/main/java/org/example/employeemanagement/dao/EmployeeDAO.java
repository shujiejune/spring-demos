package org.example.employeemanagement.dao;

import org.example.employeemanagement.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    Optional<Employee> findById(Long id);
    List<Employee> findAll();
}
