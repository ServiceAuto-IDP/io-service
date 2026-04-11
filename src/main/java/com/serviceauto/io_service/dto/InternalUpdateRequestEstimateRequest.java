package com.serviceauto.io_service.dto;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record InternalUpdateRequestEstimateRequest(@NotNull Instant estimatedResolutionTime) {
}
