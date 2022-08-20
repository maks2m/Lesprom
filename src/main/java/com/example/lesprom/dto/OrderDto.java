package com.example.lesprom.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDto {
    private Long id;
    private String numberOrder;
    private LocalDate startDate;
    private LocalDate finishDate;
    private BaguetteDto baguette;
    private CutterDto cutter;
    private Set<WorkplaceDto> workplaces = new HashSet<>();
    private Set<TimeOfEmployeeOnOrderDto> timeOfEmployeeOnOrders = new HashSet<>();
    private String orderDescription;

/*    public String getStartDate() {
        return startDate.toString();
    }

    public String getFinishDate() {
        return finishDate.toString();
    }*/
}
