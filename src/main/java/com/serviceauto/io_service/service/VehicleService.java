package com.serviceauto.io_service.service;

import com.serviceauto.io_service.dto.InternalVehicleRequest;
import com.serviceauto.io_service.dto.InternalVehicleResponse;
import com.serviceauto.io_service.entity.Vehicle;
import com.serviceauto.io_service.exception.ResourceNotFoundException;
import com.serviceauto.io_service.repository.VehicleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Transactional
    public InternalVehicleResponse createVehicle(InternalVehicleRequest request) {
        System.out.println("CREATING VEHICLE RIGHT NOW.");
        Vehicle vehicle = new Vehicle(
                request.userId(),
                request.licensePlate(),
                request.brand(),
                request.model()
        );
        return toResponse(vehicleRepository.save(vehicle));
    }

    @Transactional(readOnly = true)
    public List<InternalVehicleResponse> getVehiclesByUser(Long userId) {
        return vehicleRepository.findByUserIdOrderByIdAsc(userId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public InternalVehicleResponse getVehicleById(Long vehicleId) {
        return toResponse(findEntityById(vehicleId));
    }

    @Transactional(readOnly = true)
    public InternalVehicleResponse getVehicleByIdAndUser(Long vehicleId, Long userId) {
        Vehicle vehicle = vehicleRepository.findByIdAndUserId(vehicleId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle", vehicleId));
        return toResponse(vehicle);
    }

    @Transactional
    public InternalVehicleResponse updateVehicle(Long vehicleId, InternalVehicleRequest request) {
        Vehicle vehicle = findEntityById(vehicleId);
        vehicle.setUserId(request.userId());
        vehicle.setLicensePlate(request.licensePlate());
        vehicle.setBrand(request.brand());
        vehicle.setModel(request.model());
        return toResponse(vehicleRepository.save(vehicle));
    }

    @Transactional
    public void deleteVehicle(Long vehicleId) {
        Vehicle vehicle = findEntityById(vehicleId);
        vehicleRepository.delete(vehicle);
    }

    private Vehicle findEntityById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle", vehicleId));
    }

    private InternalVehicleResponse toResponse(Vehicle vehicle) {
        return new InternalVehicleResponse(
                vehicle.getId(),
                vehicle.getUserId(),
                vehicle.getLicensePlate(),
                vehicle.getBrand(),
                vehicle.getModel()
        );
    }
}
