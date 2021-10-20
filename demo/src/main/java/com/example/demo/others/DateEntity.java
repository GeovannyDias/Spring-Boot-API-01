package com.example.demo.others;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
// @EntityListeners(AuditingEntityListener.class)
public abstract class DateEntity {

    // @Column(columnDefinition = "TIMESTAMP", name = "created_at", nullable =
    // false, updatable = false)

    @CreatedDate
    @Column(name = "createdAt", nullable = false, updatable = false)
    private Instant createdAt; // Docs
    // private Date createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt")
    private Instant updatedAt; // Docs
    // private LocalDateTime updatedAt;

}
