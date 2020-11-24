package com.example.stackoverflow.model;

import com.example.stackoverflow.model.entity.Question;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "status_of_question", schema = "public", catalog = "stack3")
public class StatusOfQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_of_question_id", nullable = false)
    private int statusOfQuestionId;

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    @OneToMany(mappedBy = "statusOfQuestionByStatusOfQuestionId")
    private Collection<Question> questionsByStatusOfQuestionId;

}
