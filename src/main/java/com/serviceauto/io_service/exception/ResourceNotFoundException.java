package com.serviceauto.io_service.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(resourceName + " not found: " + resourceId);
    }
}
