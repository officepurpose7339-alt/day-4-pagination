package com.employee.EmpDTO.controller;

import com.employee.EmpDTO.service.EmployeeService;
import com.employee.EmpDTO.exchange.request.EmployeeRequestDTO;
import com.employee.EmpDTO.exchange.response.EmployeeResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/emp")
    public String addEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return employeeService.addEmployee(employeeRequestDTO);
    }

    @GetMapping("/empmapstruct/{id}")
    public EmployeeResponseDTO getEmployeeByIdMapStruct(@PathVariable Long id) {
        return employeeService.getEmployeeByIdMapStruct(id);
    }

    @GetMapping("/employees")
    public Page<EmployeeResponseDTO> getEmployees(@RequestParam(required = false) String department, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String direction) {
        return employeeService.getEmployees(department, page, size, sortBy, direction);
    }
}