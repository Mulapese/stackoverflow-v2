package com.example.stackoverflow.model;

import com.example.stackoverflow.model.entity.Account;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "status_of_account", schema = "public", catalog = "stack3")
public class StatusOfAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_of_account_id", nullable = false)
    private int statusOfAccountId;

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    @OneToMany(mappedBy = "statusOfAccount")
    private Collection<Account> accounts;

}
