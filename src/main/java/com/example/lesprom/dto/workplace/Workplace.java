package com.example.lesprom.dto.workplace;

import lombok.Data;

import java.util.Set;

@Data
public class Workplace {
    private Long id;
    private String nameWorkplace;
    private Set<Employee> employees;
}
