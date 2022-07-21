package com.example.lesprom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

}
