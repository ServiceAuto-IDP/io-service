package com.serviceauto.io_service.dto;

public record InternalVehicleResponse(
        Long id,
        Long userId,
        String licensePlate,
        String brand,
        String model
) {
}
