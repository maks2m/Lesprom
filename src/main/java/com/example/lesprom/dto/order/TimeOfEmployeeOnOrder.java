package com.example.lesprom.dto.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeOfEmployeeOnOrder {
    private Long id;
    private LocalDateTime timeStartWork;
    private LocalDateTime timeFinishWork;
    private Employee employee;
}
