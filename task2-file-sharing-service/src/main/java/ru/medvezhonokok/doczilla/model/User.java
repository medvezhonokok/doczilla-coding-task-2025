package ru.medvezhonokok.doczilla.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "login")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 16)
    @Pattern(regexp = "[a-z]+", message = "Only lowercase latin letters expected")
    @Column(name = "login", unique = true, nullable = false, length = 16)
    private String login;

    @Column(name = "password_sha", length = 64)
    private String passwordSha;

    @CreationTimestamp
    @Column(name = "creation_time", updatable = false)
    private Date creationTime;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordSha() {
        return passwordSha;
    }

    public void setPasswordSha(String passwordSha) {
        this.passwordSha = passwordSha;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @PrePersist
    protected void onCreate() {
        creationTime = new Date();
    }
}