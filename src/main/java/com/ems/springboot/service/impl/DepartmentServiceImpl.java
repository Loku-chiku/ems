package com.ems.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.springboot.dto.DepartmentDto;
import com.ems.springboot.entity.Department;
import com.ems.springboot.exception.ResourceNotFoundException;
import com.ems.springboot.mapper.DepartmentMapper;
import com.ems.springboot.repository.DepartmentRepository;
import com.ems.springboot.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	public DepartmentRepository departmentRepository;

	@Override
	public DepartmentDto createDepartment(DepartmentDto departmentDto) {
		Department department = DepartmentMapper.mapToDepartment(departmentDto);
		Department savedDepartment = departmentRepository.save(department);
		return DepartmentMapper.mapToDepartmentDto(savedDepartment);
	}

	@Override
	public DepartmentDto getDepartmentById(Long departmentId) {
		Department department = departmentRepository.findById(departmentId)
				.orElseThrow(
							 () -> new ResourceNotFoundException("Department is not exists with given id: " + departmentId));
		return DepartmentMapper.mapToDepartmentDto(department);
	}

	@Override
	public List<DepartmentDto> getAllDepartments() {
		List<Department> departments = departmentRepository.findAll();
		return departments.stream()
				.map((department) -> DepartmentMapper.mapToDepartmentDto(department))
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
		Department department = departmentRepository.findById(departmentId)
		.orElseThrow(
				() -> new ResourceNotFoundException("Department is not exists with given id: " + departmentId));
		department.setDepartmentName(updatedDepartment.getDepartmentName());
		department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());
		
		Department savedDepartment = departmentRepository.save(department);
		return DepartmentMapper.mapToDepartmentDto(savedDepartment);
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		departmentRepository.findById(departmentId)
		.orElseThrow(				
				() -> new ResourceNotFoundException("Department is not exists with given id: " + departmentId));
		
		departmentRepository.deleteById(departmentId);
		
		
	}

}
