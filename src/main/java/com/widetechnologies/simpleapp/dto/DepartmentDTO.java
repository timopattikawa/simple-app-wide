package com.widetechnologies.simpleapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentDTO {
    private Integer id;
    private String name;
    private String address;
    private String code;
}
