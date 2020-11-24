package com.example.stackoverflow.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id", nullable = false)
    private int urlId;

    @Basic
    @Column(name = "url", nullable = true, length = -1)
    private String url;

    @Basic
    @Column(name = "action", nullable = true, length = -1)
    private String action;

    @Basic
    @Column(name = "created_time", nullable = true)
    private Timestamp createdTime;

    @Basic
    @Column(name = "updated_time", nullable = true)
    private Timestamp updatedTime;

}
