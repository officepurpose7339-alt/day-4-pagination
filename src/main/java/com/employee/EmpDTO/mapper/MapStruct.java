package com.employee.EmpDTO.mapper;

import com.employee.EmpDTO.exchange.request.EmployeeRequestDTO;
import com.employee.EmpDTO.exchange.response.EmployeeResponseDTO;
import com.employee.EmpDTO.model.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MapStruct {

    @Mapping(source = "department.name", target = "departmentName")
    EmployeeResponseDTO toDto(Employee employee);

    @Mapping(target = "department", ignore = true)
    Employee toEntity(EmployeeRequestDTO employeeRequestDTO);
}