package com.example.stackoverflow.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Table(name = "status_of_question", schema = "public", catalog = "stack4")
public class StatusOfQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_of_question_id", nullable = false)
    private int statusOfQuestionId;

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    @OneToMany(mappedBy = "statusOfQuestion")
    private Collection<Question> questions;

}
