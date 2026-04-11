package com.serviceauto.io_service.controller;

import com.serviceauto.io_service.dto.InternalVehicleRequest;
import com.serviceauto.io_service.dto.InternalVehicleResponse;
import com.serviceauto.io_service.service.VehicleService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/vehicles")
    @ResponseStatus(HttpStatus.CREATED)
    public InternalVehicleResponse createVehicle(@Valid @RequestBody InternalVehicleRequest request) {
        return vehicleService.createVehicle(request);
    }

    @GetMapping("/users/{userId}/vehicles")
    public List<InternalVehicleResponse> getVehiclesByUser(@PathVariable Long userId) {
        return vehicleService.getVehiclesByUser(userId);
    }

    @GetMapping("/vehicles/{vehicleId}")
    public InternalVehicleResponse getVehicle(@PathVariable Long vehicleId) {
        return vehicleService.getVehicleById(vehicleId);
    }

    @PutMapping("/vehicles/{vehicleId}")
    public InternalVehicleResponse updateVehicle(
            @PathVariable Long vehicleId,
            @Valid @RequestBody InternalVehicleRequest request
    ) {
        return vehicleService.updateVehicle(vehicleId, request);
    }

    @DeleteMapping("/vehicles/{vehicleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }
}
