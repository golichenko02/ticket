package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.CommonRepository;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "transactionalVehicleService")
public class TransactionalVehicleService extends CommonService<VehicleEntity, Long> {

    @Autowired
    private VehicleRepository vehicleRepository;

    public TransactionalVehicleService(CommonRepository<VehicleEntity, Long> repository) {
        super(repository);
    }
}
