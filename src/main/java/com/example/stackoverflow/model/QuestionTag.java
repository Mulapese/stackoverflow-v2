package com.example.stackoverflow.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@IdClass(QuestionTagPK.class)
public class QuestionTag {
    @Id
    @Column(name = "question_id", nullable = false)
    private int questionId;

    @Id
    @Column(name = "tag_id", nullable = false)
    private int tagId;

    @Basic
    @Column(name = "created_time", nullable = true)
    private Timestamp createdTime;

    @Basic
    @Column(name = "updated_time", nullable = true)
    private Timestamp updatedTime;

}
