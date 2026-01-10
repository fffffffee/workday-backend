package com.example.workday.workday_backend.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目实体类，映射到数据库中的projects表
 */
@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String projectCode; // 项目编号，唯一且不能为空

    @Column(nullable = false)
    private String projectName; // 项目名称，不能为空

    private String manager; // 项目经理
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate; // 项目开始日期

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate; // 项目结束日期

    private String status; // 项目状态，如“进行中”、“已完成”等

    @Column(columnDefinition="TEXT")
    private String description; // 项目描述

}
