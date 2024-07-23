package com.ems.springboot.mapper;

import com.ems.springboot.dto.EmployeeDto;
import com.ems.springboot.entity.Employee;

public class EmployeeMapper {
	
	//Convert Employee JPA entity to Employee DTO
	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getDepartment().getId()
				);
	}
	
	//Convert Employee DTO to Employee JPA entity
	public static Employee mapToEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setId(employeeDto.getId());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmail(employeeDto.getEmail());
		return employee;
		
	}

}
