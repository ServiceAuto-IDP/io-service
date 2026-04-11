package com.serviceauto.io_service.repository;

import com.serviceauto.io_service.entity.RequestHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Long> {

    List<RequestHistory> findByRequestIdOrderByChangedAtAsc(Long requestId);
}
