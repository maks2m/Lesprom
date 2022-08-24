package com.example.lesprom.dto.timeofemployeeonorder;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeOfEmployeeOnOrder {
    private Long id;
    private LocalDateTime timeStartWork;
    private LocalDateTime timeFinishWork;
    private Employee employee;
    private Order order;
}
