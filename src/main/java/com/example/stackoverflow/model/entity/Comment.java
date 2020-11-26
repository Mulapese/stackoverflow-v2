package com.example.stackoverflow.model.entity;

import com.example.stackoverflow.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private int commentId;

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
    @Column(name = "created_time", nullable = true)
    private Timestamp createdTime;

    @Basic
    @Column(name = "updated_time", nullable = true)
    private Timestamp updatedTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "answer_id")
    private Answer answer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    public Comment(String text, Integer viewCount, Integer voteCount, Integer flagCount, Timestamp createdTime, Timestamp updatedTime, Question question, Answer answer, Account account) {
        this.text = text;
        this.viewCount = viewCount;
        this.voteCount = voteCount;
        this.flagCount = flagCount;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.question = question;
        this.answer = answer;
        this.account = account;
    }
}
