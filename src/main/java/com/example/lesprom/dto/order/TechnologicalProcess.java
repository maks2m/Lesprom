package com.example.lesprom.dto.order;

import lombok.Data;

import java.util.Date;

@Data
public class TechnologicalProcess {
    private Long id;
    private Date timeStartWork;
    private Date timeFinishWork;
    private Integer operationCode;
    private Employee employee;
    private Workplace workplace;
}
