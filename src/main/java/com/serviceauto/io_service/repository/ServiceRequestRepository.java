package com.serviceauto.io_service.repository;

import com.serviceauto.io_service.entity.ServiceRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    List<ServiceRequest> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<ServiceRequest> findByStatusIgnoreCaseOrderByCreatedAtAsc(String status);
}
