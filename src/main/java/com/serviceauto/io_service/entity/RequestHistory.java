package com.serviceauto.io_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "request_history")
public class RequestHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long requestId;

    @Column(length = 32)
    private String oldStatus;

    @Column(nullable = false, length = 32)
    private String newStatus;

    @Column(length = 1024)
    private String details;

    @Column(nullable = false)
    private Instant changedAt;

    public RequestHistory(Long requestId, String oldStatus, String newStatus, String details, Instant changedAt) {
        this.requestId = requestId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.details = details;
        this.changedAt = changedAt;
    }
}
