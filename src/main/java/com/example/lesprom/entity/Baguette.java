package com.example.lesprom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "baguette")
@Getter
@Setter
@NoArgsConstructor
public class Baguette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "baguette_name")
    private String baguetteName;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "order_baguette",
            joinColumns = @JoinColumn(name = "id_baguette", foreignKey = @ForeignKey(name = "buffer1_baguette_fk")),
            inverseJoinColumns = @JoinColumn(name = "id_order", foreignKey = @ForeignKey(name = "buffer1_order_fk")))
    private Set<Order> orders = new HashSet<>();
}
