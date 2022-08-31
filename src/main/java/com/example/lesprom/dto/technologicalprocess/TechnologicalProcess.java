package com.example.lesprom.dto.technologicalprocess;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TechnologicalProcess {
    private Long id;
    private LocalDateTime timeStartWork;
    private LocalDateTime timeFinishWork;
    private Employee employee;
    private Order order;
    private Workplace workplace;
}
