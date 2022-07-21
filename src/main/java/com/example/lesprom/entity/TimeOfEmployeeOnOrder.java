package com.example.lesprom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "time_of_employee_on_order")
@Getter
@Setter
@NoArgsConstructor
public class TimeOfEmployeeOnOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time_start_work")
    private LocalDateTime timeStartWork;

    @Column(name = "time_finish_work")
    private Long timeFinishWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", foreignKey = @ForeignKey(name = "time_of_employee_on_order_employee_fk"))
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", foreignKey = @ForeignKey(name = "time_of_employee_on_order_order_fk"))
    private Order order;

}
