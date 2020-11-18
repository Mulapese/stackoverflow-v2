package com.example.stackoverflow.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bounty", schema = "public", catalog = "stack1")
public class BountyEntity {
    private int bountyId;
    private Integer reputationPoint;
    private Timestamp expiredTime;
    private Timestamp createdTime;

    @Id
    @Column(name = "bounty_id")
    public int getBountyId() {
        return bountyId;
    }

    public void setBountyId(int bountyId) {
        this.bountyId = bountyId;
    }

    @Basic
    @Column(name = "reputation_point")
    public Integer getReputationPoint() {
        return reputationPoint;
    }

    public void setReputationPoint(Integer reputationPoint) {
        this.reputationPoint = reputationPoint;
    }

    @Basic
    @Column(name = "expired_time")
    public Timestamp getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Timestamp expiredTime) {
        this.expiredTime = expiredTime;
    }

    @Basic
    @Column(name = "created_time")
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BountyEntity that = (BountyEntity) o;
        return bountyId == that.bountyId &&
                Objects.equals(reputationPoint, that.reputationPoint) &&
                Objects.equals(expiredTime, that.expiredTime) &&
                Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bountyId, reputationPoint, expiredTime, createdTime);
    }
}
