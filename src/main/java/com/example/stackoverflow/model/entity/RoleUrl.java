package com.example.stackoverflow.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@IdClass(RoleUrlPK.class)
public class RoleUrl {
    @Id
    @Column(name = "role_id", nullable = false)
    private int roleId;

    @Id
    @Column(name = "url_id", nullable = false)
    private int urlId;

    private Timestamp createdTime;

    private Timestamp updatedTime;

}
