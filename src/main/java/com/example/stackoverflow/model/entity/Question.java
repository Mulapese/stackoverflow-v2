package com.example.stackoverflow.model.entity;

import com.example.stackoverflow.model.Bounty;
import com.example.stackoverflow.model.StatusOfQuestion;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "question", schema = "public", catalog = "stack3")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private int questionId;

    private String title;

    private String description;

    private Integer viewCount;

    private Integer voteCount;

    private Integer voteDeleteCount;

    private Integer flagCount;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    // SO-04
    // SO-06
    private Account account;

    @ManyToOne
    @JoinColumn(name = "bounty_id", referencedColumnName = "bounty_id")
    private Bounty bounty;

    @ManyToOne
    @JoinColumn(name = "status_of_question_id", referencedColumnName = "status_of_question_id")
    private StatusOfQuestion statusOfQuestion;

    @OneToMany(mappedBy = "question")
    private Collection<Answer> answers;

    @OneToMany(mappedBy = "question")
    private Collection<Comment> comments;

    @OneToMany(mappedBy = "question")
    private Collection<Vote> votes;

    public Question(String title, String description, Integer viewCount, Integer voteCount,
                    Integer voteDeleteCount, Integer flagCount, Timestamp createdTime, Timestamp updatedTime,
                    Bounty bounty, Account account, StatusOfQuestion statusOfQuestion) {
        this.title = title;
        this.description = description;
        this.viewCount = viewCount;
        this.voteCount = voteCount;
        this.voteDeleteCount = voteDeleteCount;
        this.flagCount = flagCount;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.bounty = bounty;
        this.account = account;
        this.statusOfQuestion = statusOfQuestion;
    }

}
