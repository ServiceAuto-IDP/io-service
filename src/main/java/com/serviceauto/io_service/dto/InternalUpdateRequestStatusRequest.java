package com.serviceauto.io_service.dto;

import jakarta.validation.constraints.NotBlank;

public record InternalUpdateRequestStatusRequest(@NotBlank String status) {
}
