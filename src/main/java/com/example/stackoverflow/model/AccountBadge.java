package com.example.stackoverflow.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@IdClass(AccountBadgePK.class)
public class AccountBadge {
    @Id
    @Column(name = "account_id", nullable = false)
    private int accountId;

    @Id
    @Column(name = "badge_id", nullable = false)
    private int badgeId;

    @Basic
    @Column(name = "created_time", nullable = true)
    private Timestamp createdTime;

    @Basic
    @Column(name = "updated_time", nullable = true)
    private Timestamp updatedTime;

}
