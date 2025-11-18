package com.example.workday.workday_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workday.workday_backend.repository.EmployeeRepository;
import com.example.workday.workday_backend.entity.Employee;

@RestController
@RequestMapping("/api/employees")  // Base path for all employee-related endpoints 所有API 路径前缀
@CrossOrigin(origins = "http://localhost:5173") // Adjust the origin as needed 允许跨域请求，前端默认端口5173
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // 1. Create Employee - POST /api/employees
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // 2. READ ALL EMPLOYEES - GET /api/employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    // 3. READ One EMPLOYEE - GET /api/employees/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> ResponseEntity.ok().body(employee)) // return 200 OK with employee data
                .orElse(ResponseEntity.notFound().build()); // return 404 Not Found if employee doesn't exist
    }

    // 4. UPDATE Employee - PUT /api/employees/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setEmployeeCode(employeeDetails.getEmployeeCode());
                    employee.setName(employeeDetails.getName());
                    Employee updatedEmployee = employeeRepository.save(employee);
                    return ResponseEntity.ok().body(updatedEmployee);
                }).orElse(ResponseEntity.notFound().build());
            }

    // 5. DELETE Employee - DELETE /api/employees/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // return 204 No Content
    }

}