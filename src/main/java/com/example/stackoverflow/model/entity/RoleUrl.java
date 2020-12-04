package com.example.stackoverflow.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@IdClass(RoleUrlPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
