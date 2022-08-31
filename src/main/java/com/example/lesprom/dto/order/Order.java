package com.example.lesprom.dto.order;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Order {
    private Long id;
    private String numberOrder;
    private String numberOrderOther;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String duty;
    private String color;
    private Float woodMass;
    private Float woodVeneer;
    private String radius;
    private String glass;
    private String binding;
    private String notes;

    private Set<Baguette> baguettes = new HashSet<>();
    private Set<Cutter> cutters = new HashSet<>();
    private Set<TechnologicalProcess> technologicalProcesses = new HashSet<>();

}
