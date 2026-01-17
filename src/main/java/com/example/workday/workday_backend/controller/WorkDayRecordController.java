package com.example.workday.workday_backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workday.workday_backend.entity.WorkDayRecord;
import com.example.workday.workday_backend.repository.EmployeeRepository;
import com.example.workday.workday_backend.repository.ProjectRepository;
import com.example.workday.workday_backend.repository.WorkDayRecordRepository;

@RestController
@RequestMapping("/api/workday-records")
@CrossOrigin(origins = "http://localhost:5173")
public class WorkDayRecordController {
    // 这里可以添加处理工作日记录的各种HTTP请求的方法

    @Autowired
    private WorkDayRecordRepository workDayRecordRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<WorkDayRecord> getAll() {
        List<WorkDayRecord> records = workDayRecordRepository.findAll();
        return records.stream().map(record -> {
            employeeRepository.findById(record.getEmployeeId())
                .ifPresent(employee -> record.setEmployeeName(employee.getName()));
            projectRepository.findById(record.getProjectId())
                .ifPresent(project -> record.setProjectName(project.getName()));
            return record;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public WorkDayRecord createRecord(@RequestBody WorkDayRecord record) {
        return workDayRecordRepository.save(record);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        workDayRecordRepository.deleteById(id);
    }
}
