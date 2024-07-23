package com.ems.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.springboot.dto.DepartmentDto;
import com.ems.springboot.service.DepartmentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	@Autowired
	public DepartmentService departmentService;
	
	//Build Add or Create Department REST API
	@PostMapping
	public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
		DepartmentDto savedDepartment = departmentService.createDepartment(departmentDto);
		return new ResponseEntity<>(savedDepartment,HttpStatus.CREATED);
	}
	
	//Build Get Department REST API
	@GetMapping("{id}")
	public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("id") Long departmentId){
		DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);
		return ResponseEntity.ok(departmentDto);
	}
	
	//Build Get All departments REST API
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
		List<DepartmentDto> departments = departmentService.getAllDepartments();
		return ResponseEntity.ok(departments);
	}
	
	//Build Update department REST API
	@PutMapping("{id}")
	public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId,@RequestBody DepartmentDto updatedDepartment){
		DepartmentDto departmentDto = departmentService.updateDepartment(departmentId, updatedDepartment);
		return ResponseEntity.ok(departmentDto);
	}
	
	//Build Delete department REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
		departmentService.deleteDepartment(departmentId);
		return ResponseEntity.ok("Department deleted successfully!!");
	}
	
	

}
