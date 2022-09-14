package com.example.lesprom.dto.technologicalprocess;

import lombok.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class Order {
    private Long id;
    private String numberOrder;
    private String numberOrderOther;
    private Date startDate;
    private Date finishDate;
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
}
