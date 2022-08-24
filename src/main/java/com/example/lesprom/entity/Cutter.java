package com.example.lesprom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cutter")
@Setter
@Getter
@NoArgsConstructor
public class Cutter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cutter_name")
    private String cutterName;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "order_cutter",
            joinColumns = @JoinColumn(name = "id_cutter", foreignKey = @ForeignKey(name = "buffer2_cutter_fk")),
            inverseJoinColumns = @JoinColumn(name = "id_order", foreignKey = @ForeignKey(name = "buffer2_order_fk")))
    private Set<Order> orders = new HashSet<>();

}
