package com.example.stackoverflow.model.entity;

import com.example.stackoverflow.model.entity.Account;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
