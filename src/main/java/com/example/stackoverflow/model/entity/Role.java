package com.example.stackoverflow.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private int roleId;

    private String name;

    private String description;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    @OneToMany(mappedBy = "role")
    private Collection<Account> accounts;

}
