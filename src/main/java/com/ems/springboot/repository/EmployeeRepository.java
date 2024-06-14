package com.ems.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.springboot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
