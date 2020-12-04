package com.example.stackoverflow.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id", nullable = false)
    private int urlId;

    private String url;

    private String action;

    private Timestamp createdTime;

    private Timestamp updatedTime;

}
