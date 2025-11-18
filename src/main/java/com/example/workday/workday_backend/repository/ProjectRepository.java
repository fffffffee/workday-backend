package com.example.workday.workday_backend.repository;

import com.example.workday.workday_backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // 基础的 CRUD 操作已经由 JpaRepository 提供

}
