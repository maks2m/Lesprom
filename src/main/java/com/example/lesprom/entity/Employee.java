package com.example.lesprom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TimeOfEmployeeOnOrder> timeOfEmployeeOnOrders = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "employee_workplace",
            joinColumns = @JoinColumn(name = "id_employee", foreignKey = @ForeignKey(name = "buffer_employee_fk")),
            inverseJoinColumns = @JoinColumn(name = "id_workplace", foreignKey = @ForeignKey(name = "buffer_workplace_fk")))
    private Set<Workplace> workplaces = new HashSet<>();

}