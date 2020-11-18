package com.example.stackoverflow.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tag", schema = "public", catalog = "stack1")
public class TagEntity {
    private int tagId;
    private String name;
    private String description;
    private Timestamp createdTime;

    @Id
    @Column(name = "tag_id")
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "created_time")
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagEntity tagEntity = (TagEntity) o;
        return tagId == tagEntity.tagId &&
                Objects.equals(name, tagEntity.name) &&
                Objects.equals(description, tagEntity.description) &&
                Objects.equals(createdTime, tagEntity.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, name, description, createdTime);
    }
}
