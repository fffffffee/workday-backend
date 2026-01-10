package com.example.workday.workday_backend.controller;

import com.example.workday.workday_backend.entity.Project;
import com.example.workday.workday_backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;

/**
 * 项目控制器，处理与项目相关的HTTP请求
 */
@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // CREATE: 新增项目 - POST /api/projects
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    // READ ALL: 获取所有项目 - GET /api/projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // READ ONE: 根据ID获取单个项目 - GET /api/projects/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(project -> ResponseEntity.ok().body(project))
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE: 更新项目 - PUT /api/projects/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setProjectCode(projectDetails.getProjectCode());
                    project.setProjectName(projectDetails.getProjectName());
                    project.setManager(projectDetails.getManager());
                    project.setStartDate(projectDetails.getStartDate());
                    project.setEndDate(projectDetails.getEndDate());
                    project.setStatus(projectDetails.getStatus());
                    project.setDescription(projectDetails.getDescription());
                    Project updatedProject = projectRepository.save(project);
                    return ResponseEntity.ok().body(updatedProject);
                }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE: 删除项目 - DELETE /api/projects/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (!projectRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        projectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
