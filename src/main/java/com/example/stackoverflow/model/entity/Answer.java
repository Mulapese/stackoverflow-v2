package com.example.stackoverflow.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private int answerId;

    private String text;

    private Integer viewCount;

    private Integer voteCount;

    private Integer flagCount;

    private Boolean isAccepted;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @OneToMany(mappedBy = "answer")
    private Collection<Comment> comments;

    @OneToMany(mappedBy = "answer")
    private Collection<Vote> votes;

    public Answer(String text, Integer viewCount, Integer voteCount, Integer flagCount, Boolean isAccepted, Timestamp createdTime, Timestamp updatedTime, Question question, Account account, Collection<Comment> comments) {
        this.text = text;
        this.viewCount = viewCount;
        this.voteCount = voteCount;
        this.flagCount = flagCount;
        this.isAccepted = isAccepted;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.question = question;
        this.account = account;
        this.comments = comments;
    }
}
