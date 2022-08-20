package com.example.lesprom.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeOfEmployeeOnOrderDto {

    private Long id;
    private LocalDateTime timeStartWork;
    private LocalDateTime timeFinishWork;
    private EmployeeDto employee;

}
