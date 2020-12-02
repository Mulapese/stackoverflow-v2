package com.example.stackoverflow.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Builder
public class QuestionForm {
    @NotBlank(message = "Title cannot be blank.")
    private final String title;

    @NotBlank(message = "Description cannot be blank.")
    private final String description;
}
