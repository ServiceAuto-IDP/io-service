package com.serviceauto.io_service.repository;

import com.serviceauto.io_service.entity.Vehicle;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByUserIdOrderByIdAsc(Long userId);

    Optional<Vehicle> findByIdAndUserId(Long id, Long userId);
}
