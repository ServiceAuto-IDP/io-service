package com.serviceauto.io_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record InternalCreateRequestHistoryRequest(
        @NotNull Long requestId,
        String oldStatus,
        @NotBlank String newStatus,
        @NotNull Instant changedAt,
        String details
) {
}
