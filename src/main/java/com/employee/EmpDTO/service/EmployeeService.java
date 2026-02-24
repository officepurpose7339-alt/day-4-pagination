package com.employee.EmpDTO.service;

import com.employee.EmpDTO.exchange.request.EmployeeRequestDTO;
import com.employee.EmpDTO.exchange.response.EmployeeResponseDTO;
import org.springframework.data.domain.Page;

public interface EmployeeService {

    String addEmployee(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO getEmployeeByIdMapStruct(Long id);

    Page<EmployeeResponseDTO> getEmployees(String department, int page, int size, String sortBy, String direction);
}