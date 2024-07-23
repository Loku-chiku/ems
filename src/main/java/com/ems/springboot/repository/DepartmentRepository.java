package com.ems.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.springboot.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
