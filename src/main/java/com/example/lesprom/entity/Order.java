package com.example.lesprom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_on_create")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * номер заказа
     */
    @Column(name = "number_order")
    private String numberOrder;

    /**
     * другой номер заказа
     */
    @Column(name = "number_order_other")
    private String numberOrderOther;

    /**
     * дата начала работ по заказу
     */
    @Column(name = "start_date")
    private LocalDate startDate;

    /**
     * дата окончания работ по заказу
     */
    @Column(name = "finish_date")
    private LocalDate finishDate;

    /**
     * долг по заказу
     */
    @Column(name = "duty")
    private String duty;

    /**
     * цвет изделия
     */
    @Column(name = "color")
    private String color;

    /**
     * массив дерева
     */
    @Column(name = "wood_mass")
    private Float woodMass;

    /**
     * шпон дерева
     */
    @Column(name = "wood_veneer")
    private Float woodVeneer;

    /**
     * радиус
     */
    @Column(name = "radius")
    private String radius;

    /**
     * наличие стекла в заказе
     */
    @Column(name = "glass")
    private String glass;

    /**
     * наличие переплета в заказе
     */
    @Column(name = "binding")
    private String binding;

    /**
     * примечание
     */
    @Column(name = "notes")
    private String notes;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "order_baguette",
            joinColumns = @JoinColumn(name = "id_order", foreignKey = @ForeignKey(name = "buffer1_order_fk")),
            inverseJoinColumns = @JoinColumn(name = "id_baguette", foreignKey = @ForeignKey(name = "buffer1_baguette_fk")))
    private Set<Baguette> baguettes = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "order_cutter",
            joinColumns = @JoinColumn(name = "id_order", foreignKey = @ForeignKey(name = "buffer2_order_fk")),
            inverseJoinColumns = @JoinColumn(name = "id_cutter", foreignKey = @ForeignKey(name = "buffer2_cutter_fk")))
    private Set<Cutter> cutters = new HashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TimeOfEmployeeOnOrder> timeOfEmployeeOnOrders = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "order_workplace",
            joinColumns = @JoinColumn(name = "id_order", foreignKey = @ForeignKey(name = "buffer_order_fk")),
            inverseJoinColumns = @JoinColumn(name = "id_workplace", foreignKey = @ForeignKey(name = "buffer_workplace_fk")))
    private Set<Workplace> workplaces = new HashSet<>();
}
