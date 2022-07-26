package com.example.lesprom.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDto {
    private Long id;
    private Integer numberOrder;
    private Set<WorkplaceDto> workplaces = new HashSet<>();;
}
