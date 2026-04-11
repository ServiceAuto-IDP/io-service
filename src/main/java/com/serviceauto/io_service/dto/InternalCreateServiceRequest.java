package com.serviceauto.io_service.dto;

import com.serviceauto.io_service.model.RequestCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record InternalCreateServiceRequest(
        @NotNull Long userId,
        @NotNull Long vehicleId,
        @NotNull RequestCategory category,
        @NotBlank String description,
        @NotBlank String status,
        @NotNull Instant createdAt
) {
}
