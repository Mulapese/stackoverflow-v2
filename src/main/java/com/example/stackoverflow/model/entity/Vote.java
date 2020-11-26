package com.example.stackoverflow.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id", nullable = false)
    private int voteId;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "comment_id")
    private Comment comment;

    private int score;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    public Vote(int voteId, Account account, Question question, Answer answer, Comment comment, int score, Timestamp createdTime, Timestamp updatedTime) {
        this.voteId = voteId;
        this.account = account;
        this.question = question;
        this.answer = answer;
        this.comment = comment;
        this.score = score;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}
