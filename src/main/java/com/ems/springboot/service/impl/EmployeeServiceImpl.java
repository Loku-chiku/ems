package com.ems.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.springboot.dto.EmployeeDto;
import com.ems.springboot.entity.Department;
import com.ems.springboot.entity.Employee;
import com.ems.springboot.exception.ResourceNotFoundException;
import com.ems.springboot.mapper.EmployeeMapper;
import com.ems.springboot.repository.DepartmentRepository;
import com.ems.springboot.repository.EmployeeRepository;
import com.ems.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		
		Department department = departmentRepository.findById(employeeDto.getDepartmentId())
				.orElseThrow(
				() -> new ResourceNotFoundException("Department is not exists with id: " + employeeDto.getDepartmentId()));
		
		employee.setDepartment(department);
				
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
		.orElseThrow(() -> 
		          new ResourceNotFoundException("Employee is not exists with given id:" + employeeId));

		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
		Employee employee = employeeRepository.findById(employeeId)
							.orElseThrow(
							() -> new ResourceNotFoundException("Employee is not exists with given id:" + employeeId));
		
		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmail(updatedEmployee.getEmail());
		
		Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
				.orElseThrow(
				() -> new ResourceNotFoundException("Department is not exists with id: " + updatedEmployee.getDepartmentId()));
		
		employee.setDepartment(department);
				
		
		Employee updatedEmployeeObj = employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		employeeRepository.findById(employeeId)
				.orElseThrow(() -> 
				          new ResourceNotFoundException("Employee is not exists with given id:" + employeeId));
		employeeRepository.deleteById(employeeId);
		
	}

}
