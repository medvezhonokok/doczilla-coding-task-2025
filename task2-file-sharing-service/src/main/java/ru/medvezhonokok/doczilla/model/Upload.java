package ru.medvezhonokok.doczilla.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "uploads")
public class Upload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String hashedFileName;

    @CreationTimestamp
    @Column(name = "upload_time", updatable = false)
    private Date uploadTime;

    public String getHashedFileName() {
        return hashedFileName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setHashedFileName(String hashedFileName) {
        this.hashedFileName = hashedFileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @PrePersist
    protected void onCreate() {
        uploadTime = new Date();
    }
}