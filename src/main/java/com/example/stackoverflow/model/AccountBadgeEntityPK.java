package com.example.stackoverflow.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AccountBadgeEntityPK implements Serializable {
    private int accountId;
    private int badgeId;

    @Column(name = "account_id")
    @Id
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Column(name = "badge_id")
    @Id
    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountBadgeEntityPK that = (AccountBadgeEntityPK) o;
        return accountId == that.accountId &&
                badgeId == that.badgeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, badgeId);
    }
}
