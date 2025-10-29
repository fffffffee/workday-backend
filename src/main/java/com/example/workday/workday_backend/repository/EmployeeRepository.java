package com.example.workday.workday_backend.repository;

import java.util.Optional;
import com.example.workday.workday_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Spring Data JPA 允许我们通过方法命名规范来定义自定义查询
    Optional<Employee> findByEmployeeCode(String employeeCode);
}
