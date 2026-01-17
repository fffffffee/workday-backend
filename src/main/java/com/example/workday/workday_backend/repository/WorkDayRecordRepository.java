package com.example.workday.workday_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.workday.workday_backend.entity.WorkDayRecord;

@Repository
public interface  WorkDayRecordRepository extends JpaRepository<WorkDayRecord, Long> {
    // 基础的 CRUD 操作已经由 JpaRepository 提供


}
