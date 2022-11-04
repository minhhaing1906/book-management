package com.example.book.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_accounts")
@NoArgsConstructor
@Getter
@Setter
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @NotBlank(message = "Must give an username")
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String userName;

    @NotBlank(message = "Must give an email")
    @Email(message = "Enter a valid email address")
    @Column(name = "email")
    private String email;

    @Size(min = 6, message = "Password must have at least 6 characters")
    @NotBlank(message = "Must give a password")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role = "ROLE_AUTHENTICATED";

    @Column(name = "enabled")
    private boolean enabled = true;
}
