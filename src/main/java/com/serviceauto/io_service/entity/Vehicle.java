package com.serviceauto.io_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true, length = 32)
    private String licensePlate;

    @Column(nullable = false, length = 64)
    private String brand;

    @Column(nullable = false, length = 64)
    private String model;

    public Vehicle(Long userId, String licensePlate, String brand, String model) {
        this.userId = userId;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
    }
}
