package com.example.stackoverflow.model.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class QuestionForm {
    @NotBlank(message = "Title cannot be blank.")
    private String title;

    @NotBlank(message = "Description cannot be blank.")
    private String description;
}
