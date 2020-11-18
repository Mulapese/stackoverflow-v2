package com.example.stackoverflow.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class QuestionTagEntityPK implements Serializable {
    private int questionId;
    private int tagId;

    @Column(name = "question_id")
    @Id
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Column(name = "tag_id")
    @Id
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionTagEntityPK that = (QuestionTagEntityPK) o;
        return questionId == that.questionId &&
                tagId == that.tagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, tagId);
    }
}
