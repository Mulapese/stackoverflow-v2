package com.example.stackoverflow.model;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "account", schema = "public", catalog = "stack1")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int accountId;

    @NotNull(message = "Name cannot be null.")
    private Integer actorId;

    @NotNull(message = "Name cannot be null.")
    private String name;

//    @Email(message = "Must be a well-formed email address.")
    private String email;

    @Pattern(regexp = "\\d+", message = "The password has minium 8 characters.")
    private String password;
    private String address;
    private String phone;
    private String status;
    private Integer reputationPoint;
    private Timestamp createdTime;
}
