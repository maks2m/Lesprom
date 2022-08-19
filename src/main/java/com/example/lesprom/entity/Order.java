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

    @Column(name = "number_order")
    private String numberOrder;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "finish_date")
    private LocalDate finishDate;

/*
    @Column(name = "color")
    private String color;

    @Column(name = "wood_mass")
    private Float woodMass;

    @Column(name = "wood_veneer")
    private Float woodVeneer;

    @Column(name = "radius")
    private String radius;

    @Column(name = "glass")
    private String glass;

    @Column(name = "binding")
    private String binding;
*/

    /** описание заказа
     *  включает в себя:
     *  - радиус
     *  - стекло
     *  - переплет
     *  - массив
     *  - шпон
     *  - цвет
     */
    @Column(name = "order_description")
    private String orderDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_baguette", foreignKey = @ForeignKey(name = "order_baguette_fk"))
    private Baguette baguette;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cutter", foreignKey = @ForeignKey(name = "order_cutter_fk"))
    private Cutter cutter;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TimeOfEmployeeOnOrder> timeOfEmployeeOnOrders = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "order_workplace",
            joinColumns = @JoinColumn(name = "id_order", foreignKey = @ForeignKey(name = "buffer_order_fk")),
            inverseJoinColumns = @JoinColumn(name = "id_workplace", foreignKey = @ForeignKey(name = "buffer_workplace_fk")))
    private Set<Workplace> workplaces = new HashSet<>();
}
