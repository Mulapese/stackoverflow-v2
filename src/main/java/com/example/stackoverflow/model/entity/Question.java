package com.example.stackoverflow.model.entity;

import com.example.stackoverflow.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "question", schema = "public", catalog = "stack3")
public class Question {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account accountByAccountId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private int questionId;

    @Size(min = 1, max = 256, message = "Title must be between 1 and 256 characters.")
    @NotNull(message = "Title cannot be null.")
    private String title;

    @NotNull(message = "Title cannot be null.")
    @Size(min = 1, message = "Title cannot be empty.")
    private String description;

    @Basic
    @Column(name = "view_count", nullable = true)
    private Integer viewCount;

    @Basic
    @Column(name = "vote_close_count", nullable = true)
    private Integer voteCloseCount;

    @Basic
    @Column(name = "vote_delete_count", nullable = true)
    private Integer voteDeleteCount;

    @Basic
    @Column(name = "flag_count", nullable = true)
    private Integer flagCount;

    @Basic
    @Column(name = "created_time", nullable = true)
    private Timestamp createdTime;

    @Basic
    @Column(name = "updated_time", nullable = true)
    private Timestamp updatedTime;

    @JsonIgnore
    @OneToMany(mappedBy = "questionByQuestionId")
    private Collection<Answer> answersByQuestionId;

    @JsonIgnore
    @OneToMany(mappedBy = "questionByQuestionId")
    private Collection<Comment> commentsByQuestionId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bounty_id", referencedColumnName = "bounty_id")
    private Bounty bountyByBountyId;

    public Question(String title, String description, Integer viewCount, Integer voteCloseCount,
                    Integer voteDeleteCount, Integer flagCount, Timestamp createdTime, Timestamp updatedTime,
                    Bounty bountyByBountyId, Account accountByAccountId, StatusOfQuestion statusOfQuestionByStatusOfQuestionId) {
        this.title = title;
        this.description = description;
        this.viewCount = viewCount;
        this.voteCloseCount = voteCloseCount;
        this.voteDeleteCount = voteDeleteCount;
        this.flagCount = flagCount;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.bountyByBountyId = bountyByBountyId;
        this.accountByAccountId = accountByAccountId;
        this.statusOfQuestionByStatusOfQuestionId = statusOfQuestionByStatusOfQuestionId;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "status_of_question_id", referencedColumnName = "status_of_question_id")
    private StatusOfQuestion statusOfQuestionByStatusOfQuestionId;

}
