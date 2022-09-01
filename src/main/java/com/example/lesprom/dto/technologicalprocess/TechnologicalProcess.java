package com.example.lesprom.dto.technologicalprocess;

import lombok.Data;

import java.util.Date;

@Data
public class TechnologicalProcess {
    private Long id;
    private Date timeStartWork;
    private Date timeFinishWork;
    private Integer operationCode;
    private Employee employee;
    private Order order;
    private Workplace workplace;
}
