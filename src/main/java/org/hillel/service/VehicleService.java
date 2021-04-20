package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;

public interface VehicleService {
    VehicleEntity createOrUpdateVehicle(final VehicleEntity vehicleEntity);
}
