package com.example.stackoverflow.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
public class Bounty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bounty_id", nullable = false)
    private int bountyId;

    @Basic
    @Column(name = "reputation_point", nullable = true)
    private Integer reputationPoint;

    @Basic
    @Column(name = "expired_time", nullable = true)
    private Timestamp expiredTime;

    @Basic
    @Column(name = "created_time", nullable = true)
    private Timestamp createdTime;

    @Basic
    @Column(name = "updated_time", nullable = true)
    private Timestamp updatedTime;

    @OneToMany(mappedBy = "bountyByBountyId")
    private Collection<Question> questionsByBountyId;

}
