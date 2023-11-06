package com.widetechnologies.simpleapp.repository;

import com.widetechnologies.simpleapp.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Optional<Employee> getEmployeesByEmail(String email);
}
