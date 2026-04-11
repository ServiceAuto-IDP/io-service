package com.serviceauto.io_service.dto;

import java.time.Instant;

public record InternalRequestHistoryResponse(
        Long id,
        Long requestId,
        String oldStatus,
        String newStatus,
        Instant changedAt
) {
}
