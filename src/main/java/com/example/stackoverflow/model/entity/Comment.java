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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private int commentId;

    private String text;

    @Builder.Default
    private Integer viewCount = 0;

    @Builder.Default
    private Integer voteCount = 0;

    @Builder.Default
    private Integer flagCount = 0;

    @Builder.Default
    private Timestamp createdTime = Utils.getCurrentTimeStamp();

    @Builder.Default
    private Timestamp updatedTime = Utils.getCurrentTimeStamp();

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @OneToMany(mappedBy = "comment")
    private Collection<Vote> votes;

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
