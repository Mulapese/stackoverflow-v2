package com.example.stackoverflow.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class QuestionTagPK implements Serializable {
    @Column(name = "question_id", nullable = false)
    @Id
    private int questionId;

    @Column(name = "tag_id", nullable = false)
    @Id
    private int tagId;

}
