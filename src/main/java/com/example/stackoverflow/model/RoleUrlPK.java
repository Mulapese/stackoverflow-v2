package com.example.stackoverflow.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class RoleUrlPK implements Serializable {
    @Column(name = "role_id", nullable = false)
    @Id
    private int roleId;

    @Column(name = "url_id", nullable = false)
    @Id
    private int urlId;

}
