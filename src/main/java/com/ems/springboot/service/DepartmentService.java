package com.ems.springboot.service;

import java.util.List;

import com.ems.springboot.dto.DepartmentDto;

public interface DepartmentService {
	
	public DepartmentDto createDepartment(DepartmentDto departmentDto);
	
	public DepartmentDto getDepartmentById(Long departmentId);
	
	public List<DepartmentDto> getAllDepartments();
	
	public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment);
	
	public void deleteDepartment(Long departmentId);
}
