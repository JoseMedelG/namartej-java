package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}