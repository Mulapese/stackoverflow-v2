package com.example.stackoverflow.model.entity;

import com.example.stackoverflow.model.Account;
import com.example.stackoverflow.model.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private int answerId;

    @Basic
    @Column(name = "text", nullable = true, length = -1)
    private String text;

    @Basic
    @Column(name = "view_count", nullable = true)
    private Integer viewCount;

    @Basic
    @Column(name = "vote_count", nullable = true)
    private Integer voteCount;

    @Basic
    @Column(name = "flag_count", nullable = true)
    private Integer flagCount;

    @Basic
    @Column(name = "is_accepted", nullable = true)
    private Boolean isAccepted;

    @Basic
    @Column(name = "created_time", nullable = true)
    private Timestamp createdTime;

    @Basic
    @Column(name = "updated_time", nullable = true)
    private Timestamp updatedTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question questionByQuestionId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account accountByAccountId;

    @JsonIgnore
    @OneToMany(mappedBy = "answerByAnswerId")
    private Collection<Comment> commentsByAnswerId;

}
