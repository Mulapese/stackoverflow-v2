package com.example.stackoverflow.model.entity;

import com.example.stackoverflow.common.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private int answerId;

    private String text;

    @Builder.Default
    private Integer viewCount = 0;

    @Builder.Default
    private Integer voteCount = 0;

    @Builder.Default
    private Integer flagCount = 0;

    @Builder.Default
    private Boolean isAccepted = false;

    @Builder.Default
    private Timestamp createdTime = Utils.getCurrentTimeStamp();

    @Builder.Default
    private Timestamp updatedTime = Utils.getCurrentTimeStamp();

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
