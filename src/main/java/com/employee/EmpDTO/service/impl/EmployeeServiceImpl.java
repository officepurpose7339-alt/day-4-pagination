package com.employee.EmpDTO.service.impl;

import com.employee.EmpDTO.Exceptions.EmployeeNotFoundException;
import com.employee.EmpDTO.exchange.request.EmployeeRequestDTO;
import com.employee.EmpDTO.exchange.response.EmployeeResponseDTO;
import com.employee.EmpDTO.mapper.MapStruct;
import com.employee.EmpDTO.model.Department;
import com.employee.EmpDTO.model.Employee;
import com.employee.EmpDTO.repository.DepartmentRepository;
import com.employee.EmpDTO.repository.EmployeeRepository;
import com.employee.EmpDTO.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private MapStruct mapStruct;

    @Override
    public String addEmployee(EmployeeRequestDTO employeeRequestDTO) {

        Employee employee = mapStruct.toEntity(employeeRequestDTO);

        Department department = departmentRepository.findByName(employeeRequestDTO.getDepartmentName()).orElseGet(() -> {
                    Department newDept = new Department();
                    newDept.setName(employeeRequestDTO.getDepartmentName());
                    return departmentRepository.save(newDept);
                });

        employee.setDepartment(department);

        employeeRepository.save(employee);

        return "Success";
    }

    @Override
    public EmployeeResponseDTO getEmployeeByIdMapStruct(Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        return mapStruct.toDto(employee);
    }

    @Override
    public Page<EmployeeResponseDTO> getEmployees(String department, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Employee> employeePage;

        if (department != null && !department.isBlank())
        {
            employeePage = employeeRepository.findByDepartment_Name(department, pageable);
        }
        else
        {
            employeePage = employeeRepository.findAll(pageable);
        }

        return employeePage.map(mapStruct::toDto);
    }
}