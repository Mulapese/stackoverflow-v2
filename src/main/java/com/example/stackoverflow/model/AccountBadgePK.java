package com.example.stackoverflow.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class AccountBadgePK implements Serializable {
    @Column(name = "account_id", nullable = false)
    @Id
    private int accountId;

    @Column(name = "badge_id", nullable = false)
    @Id
    private int badgeId;

}
