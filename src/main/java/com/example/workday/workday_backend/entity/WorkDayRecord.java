package com.example.workday.workday_backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;


@Entity
@Table(name = "workday_records")
@Data
public class WorkDayRecord {

    // 这里定义 WorkDayRecord 实体的字段和方法
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false)
    private LocalDate workDate; // 工作日期

    @Column(nullable = false)
    private Double duration; // 工作时长

    private String remark; // 备注信息

    // 建议：为了前端显示方便，我们可以在这里增加两个非数据库字段
    @Transient
    private String employeeName; // 员工姓名，非数据库字段

    @Transient
    private String projectName; // 项目名称，非数据库字段
}

