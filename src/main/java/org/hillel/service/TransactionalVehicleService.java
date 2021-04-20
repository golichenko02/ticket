package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "transactionalVehicleService")
public class TransactionalVehicleService implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    @Override
    public VehicleEntity createOrUpdateVehicle(VehicleEntity vehicleEntity) {
        return vehicleRepository.createOrUpdate(vehicleEntity);
    }

}
