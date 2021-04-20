package org.hillel.service;

import org.hillel.persistence.entity.VehicleSeatEntity;
import org.hillel.persistence.repository.VehicleSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "transactionalVehicleSeatService")
public class TransactionalVehicleSeatService implements VehicleSeatService {

    @Autowired
    VehicleSeatRepository vehicleSeatRepository;

    @Transactional
    @Override
    public VehicleSeatEntity createOrUpdateSeat(VehicleSeatEntity vehicleSeatEntity) {
        return vehicleSeatRepository.createOrUpdate(vehicleSeatEntity);
    }
}
