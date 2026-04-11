package com.serviceauto.io_service.controller;

import com.serviceauto.io_service.dto.InternalCreateRequestHistoryRequest;
import com.serviceauto.io_service.dto.InternalCreateServiceRequest;
import com.serviceauto.io_service.dto.InternalRequestHistoryResponse;
import com.serviceauto.io_service.dto.InternalServiceRequestResponse;
import com.serviceauto.io_service.dto.InternalUpdateRequestEstimateRequest;
import com.serviceauto.io_service.dto.InternalUpdateRequestStatusRequest;
import com.serviceauto.io_service.service.RequestHistoryService;
import com.serviceauto.io_service.service.ServiceRequestService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;
    private final RequestHistoryService requestHistoryService;

    @PostMapping("/requests")
    @ResponseStatus(HttpStatus.CREATED)
    public InternalServiceRequestResponse createRequest(@Valid @RequestBody InternalCreateServiceRequest request) {
        return serviceRequestService.createRequest(request);
    }

    @GetMapping("/users/{userId}/requests")
    public List<InternalServiceRequestResponse> getRequestsByUser(@PathVariable Long userId) {
        return serviceRequestService.getRequestsByUser(userId);
    }

    @GetMapping("/requests/{requestId}")
    public InternalServiceRequestResponse getRequest(@PathVariable Long requestId) {
        return serviceRequestService.getRequestById(requestId);
    }

    @GetMapping("/requests/status/{status}")
    public List<InternalServiceRequestResponse> getRequestsByStatus(@PathVariable String status) {
        return serviceRequestService.getRequestsByStatus(status);
    }

    @PutMapping("/requests/{requestId}/status")
    public InternalServiceRequestResponse updateStatus(
            @PathVariable Long requestId,
            @Valid @RequestBody InternalUpdateRequestStatusRequest request
    ) {
        return serviceRequestService.updateStatus(requestId, request.status());
    }

    @PutMapping("/requests/{requestId}/estimate")
    public InternalServiceRequestResponse updateEstimate(
            @PathVariable Long requestId,
            @Valid @RequestBody InternalUpdateRequestEstimateRequest request
    ) {
        return serviceRequestService.updateEstimate(requestId, request.estimatedResolutionTime());
    }

    @GetMapping("/requests/{requestId}/history")
    public List<InternalRequestHistoryResponse> getHistory(@PathVariable Long requestId) {
        return requestHistoryService.getHistoryForRequest(requestId);
    }

    @PostMapping("/requests/{requestId}/history")
    @ResponseStatus(HttpStatus.CREATED)
    public InternalRequestHistoryResponse addHistory(
            @PathVariable Long requestId,
            @Valid @RequestBody InternalCreateRequestHistoryRequest request
    ) {
        return requestHistoryService.addHistoryEntry(requestId, request);
    }
}
