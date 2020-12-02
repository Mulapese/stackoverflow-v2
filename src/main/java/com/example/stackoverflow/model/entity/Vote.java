package com.example.stackoverflow.model.entity;

import com.example.stackoverflow.common.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Builder.Default
    private Timestamp createdTime = Utils.getCurrentTimeStamp();

    @Builder.Default
    private Timestamp updatedTime = Utils.getCurrentTimeStamp();

}
