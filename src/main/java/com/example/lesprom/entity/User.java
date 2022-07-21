package com.example.lesprom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "workplace")
    private String workplace;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "buffer_user_fk")),
            inverseJoinColumns = @JoinColumn(name = "id_role", foreignKey = @ForeignKey(name = "buffer_role_fk")))
    private Set<Role> roles = new HashSet<>();

}
