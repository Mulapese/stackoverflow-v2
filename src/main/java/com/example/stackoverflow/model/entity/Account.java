package com.example.stackoverflow.model.entity;

import com.example.stackoverflow.model.Role;
import com.example.stackoverflow.model.StatusOfAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private int accountId;

    @Size(min = 1, max = 256, message = "Name must be between 1 and 256 characters")
    @NotNull(message = "Name cannot be null.")
    private String name;

    @Email(message = "Email address is invalid.")
    @Column(name = "email", nullable = true, length = -1)
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Passwords must contain at least eight characters, including at least 1 letter and 1 number.")
    private String password;

    private String address;

    @NotNull(message = "Phone cannot be null.")
    private String phone;

    @PositiveOrZero(message = "Reputation point must be greater than or equal 0.")
    @Max(value = Integer.MAX_VALUE, message = "Reputation point must be smaller than 2.147.483.687.")
    private Integer reputationPoint;

    private Timestamp createdTime;

    private Timestamp updatedTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "status_of_account_id", referencedColumnName = "status_of_account_id")
    private StatusOfAccount statusOfAccountByStatusOfAccountId;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private Collection<Answer> answers;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private Collection<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private Collection<Question> questions;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private Collection<Vote> votes;

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                '}';
    }
}
