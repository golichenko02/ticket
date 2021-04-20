package org.hillel.service;

import org.hillel.persistence.entity.VehicleSeatEntity;

public interface VehicleSeatService {
    VehicleSeatEntity createOrUpdateSeat(final VehicleSeatEntity vehicleSeatEntity);

}
