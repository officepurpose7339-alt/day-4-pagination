package com.employee.EmpDTO.exchange.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeRequestDTO {

    @NotBlank(message = "Name cannot be empty")
    String name;
    @NotBlank(message = "Role cannot be empty")
    String role;
    @Email(message = "Mail ID format is incorrect")
    String email;
    @NotBlank(message = "Department cannot be empty")
    String departmentName;
}