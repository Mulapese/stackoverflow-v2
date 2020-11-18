package com.example.stackoverflow.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "question", schema = "public", catalog = "stack1")
public class QuestionEntity {
    private int questionId;
    private Integer bountyId;
    private Integer accountId;
    private String title;
    private String description;
    private Integer viewCount;
    private Integer voteCloseCount;
    private Integer voteDelete;
    private Integer flagCount;
    private String status;
    private Timestamp createdTime;

    @Id
    @Column(name = "question_id")
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "bounty_id")
    public Integer getBountyId() {
        return bountyId;
    }

    public void setBountyId(Integer bountyId) {
        this.bountyId = bountyId;
    }

    @Basic
    @Column(name = "account_id")
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "view_count")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @Basic
    @Column(name = "vote_close_count")
    public Integer getVoteCloseCount() {
        return voteCloseCount;
    }

    public void setVoteCloseCount(Integer voteCloseCount) {
        this.voteCloseCount = voteCloseCount;
    }

    @Basic
    @Column(name = "vote_delete")
    public Integer getVoteDelete() {
        return voteDelete;
    }

    public void setVoteDelete(Integer voteDelete) {
        this.voteDelete = voteDelete;
    }

    @Basic
    @Column(name = "flag_count")
    public Integer getFlagCount() {
        return flagCount;
    }

    public void setFlagCount(Integer flagCount) {
        this.flagCount = flagCount;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        QuestionEntity that = (QuestionEntity) o;
        return questionId == that.questionId &&
                Objects.equals(bountyId, that.bountyId) &&
                Objects.equals(accountId, that.accountId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(viewCount, that.viewCount) &&
                Objects.equals(voteCloseCount, that.voteCloseCount) &&
                Objects.equals(voteDelete, that.voteDelete) &&
                Objects.equals(flagCount, that.flagCount) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, bountyId, accountId, title, description, viewCount, voteCloseCount, voteDelete, flagCount, status, createdTime);
    }
}
