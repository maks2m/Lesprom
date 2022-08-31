package com.example.lesprom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workplace")
@Getter
@Setter
@NoArgsConstructor
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_workplace")
    private String nameWorkplace;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "employee_workplace",
            joinColumns = @JoinColumn(name = "id_workplace", foreignKey = @ForeignKey(name = "buffer_workplace_fk")),
            inverseJoinColumns = @JoinColumn(name = "id_employee", foreignKey = @ForeignKey(name = "buffer_employee_fk")))
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "workplace", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Set<TechnologicalProcess> technologicalProcesses = new HashSet<>();

}
