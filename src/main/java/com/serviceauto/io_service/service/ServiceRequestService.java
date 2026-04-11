package com.serviceauto.io_service.service;

import com.serviceauto.io_service.dto.InternalCreateServiceRequest;
import com.serviceauto.io_service.dto.InternalServiceRequestResponse;
import com.serviceauto.io_service.entity.ServiceRequest;
import com.serviceauto.io_service.exception.ResourceNotFoundException;
import com.serviceauto.io_service.model.RequestStatus;
import com.serviceauto.io_service.repository.ServiceRequestRepository;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;

    @Transactional
    public InternalServiceRequestResponse createRequest(InternalCreateServiceRequest request) {
        String normalizedStatus = normalizeStatus(request.status());
        ServiceRequest serviceRequest = new ServiceRequest(
                request.userId(),
                request.vehicleId(),
                request.category(),
                request.description(),
                normalizedStatus,
                request.createdAt()
        );
        return toResponse(serviceRequestRepository.save(serviceRequest));
    }

    @Transactional(readOnly = true)
    public List<InternalServiceRequestResponse> getRequestsByUser(Long userId) {
        return serviceRequestRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public InternalServiceRequestResponse getRequestById(Long requestId) {
        return toResponse(findEntityById(requestId));
    }

    @Transactional
    public InternalServiceRequestResponse updateStatus(Long requestId, String status) {
        ServiceRequest request = findEntityById(requestId);
        request.setStatus(normalizeStatus(status));
        request.setUpdatedAt(Instant.now());
        return toResponse(serviceRequestRepository.save(request));
    }

    @Transactional
    public InternalServiceRequestResponse updateEstimate(Long requestId, Instant estimate) {
        ServiceRequest request = findEntityById(requestId);
        request.setEstimatedResolutionTime(estimate);
        request.setUpdatedAt(Instant.now());
        return toResponse(serviceRequestRepository.save(request));
    }

    @Transactional(readOnly = true)
    public List<InternalServiceRequestResponse> getRequestsByStatus(String status) {
        return serviceRequestRepository.findByStatusIgnoreCaseOrderByCreatedAtAsc(normalizeStatus(status)).stream()
                .map(this::toResponse)
                .toList();
    }

    private String normalizeStatus(String status) {
        return RequestStatus.fromValue(status).getValue();
    }

    void ensureRequestExists(Long requestId) {
        findEntityById(requestId);
    }

    private ServiceRequest findEntityById(Long requestId) {
        return serviceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", requestId));
    }

    private InternalServiceRequestResponse toResponse(ServiceRequest request) {
        return new InternalServiceRequestResponse(
                request.getId(),
                request.getUserId(),
                request.getVehicleId(),
                request.getCategory(),
                request.getDescription(),
                request.getStatus(),
                request.getEstimatedResolutionTime(),
                request.getCreatedAt(),
                request.getUpdatedAt()
        );
    }
}
