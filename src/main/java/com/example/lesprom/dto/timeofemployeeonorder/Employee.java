package com.example.lesprom.dto.timeofemployeeonorder;

import lombok.Data;

import java.util.Set;

@Data
public class Employee {
    private Long id;
    private String fullName;
    private Set<Workplace> workplaces;
}