package com.serviceauto.io_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InternalVehicleRequest(
        @NotNull Long userId,
        @NotBlank String plateNumber,
        @NotBlank String brand,
        @NotBlank String model
) {
}
