package com.example.lesprom.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class WorkplaceDto {
    private Long id;
    private String nameWorkplace;
    private Set<EmployeeDto> employees = new HashSet<>();
}
