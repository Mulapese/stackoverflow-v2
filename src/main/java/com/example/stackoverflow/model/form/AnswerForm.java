package com.example.stackoverflow.model.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AnswerForm {
    @NotBlank(message = "Text cannot be blank.")
    private String text;
}
