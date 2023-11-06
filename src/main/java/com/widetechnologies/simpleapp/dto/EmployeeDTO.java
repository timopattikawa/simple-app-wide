package com.widetechnologies.simpleapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String email;
    private Integer age;
    private Integer departmentId;
}
