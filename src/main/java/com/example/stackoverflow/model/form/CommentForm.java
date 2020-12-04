package com.example.stackoverflow.model.form;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentForm {
    @NotBlank(message = "Text cannot be blank.")
    private String text;
}
