package com.example.stackoverflow.model.form;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter

public class QuestionForm {
    @Size(min = 1, max = 256, message = "Title must be between 1 and 256 characters.")
    @NotNull(message = "Title cannot be null.")
    private String title;

    @NotNull(message = "Title cannot be null.")
    @Size(min = 1, message = "Title cannot be empty.")
    private String description;
}
