package com.serviceauto.io_service.service;

import com.serviceauto.io_service.dto.InternalCreateRequestHistoryRequest;
import com.serviceauto.io_service.dto.InternalRequestHistoryResponse;
import com.serviceauto.io_service.entity.RequestHistory;
import com.serviceauto.io_service.repository.RequestHistoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequestHistoryService {

    private final RequestHistoryRepository requestHistoryRepository;
    private final ServiceRequestService serviceRequestService;

    @Transactional
    public InternalRequestHistoryResponse addHistoryEntry(Long requestId, InternalCreateRequestHistoryRequest request) {
        serviceRequestService.ensureRequestExists(requestId);
        RequestHistory history = new RequestHistory(
                requestId,
                request.oldStatus(),
                request.newStatus(),
                request.details(),
                request.changedAt()
        );
        return toResponse(requestHistoryRepository.save(history));
    }

    @Transactional(readOnly = true)
    public List<InternalRequestHistoryResponse> getHistoryForRequest(Long requestId) {
        serviceRequestService.ensureRequestExists(requestId);
        return requestHistoryRepository.findByRequestIdOrderByChangedAtAsc(requestId).stream()
                .map(this::toResponse)
                .toList();
    }

    private InternalRequestHistoryResponse toResponse(RequestHistory history) {
        return new InternalRequestHistoryResponse(
                history.getId(),
                history.getRequestId(),
                history.getOldStatus(),
                history.getNewStatus(),
                history.getChangedAt()
        );
    }
}
