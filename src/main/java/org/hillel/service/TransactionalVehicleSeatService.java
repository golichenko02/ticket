package org.hillel.service;

import org.hillel.persistence.entity.VehicleSeatEntity;
import org.hillel.persistence.repository.CommonRepository;
import org.hillel.persistence.repository.VehicleSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "transactionalVehicleSeatService")
public class TransactionalVehicleSeatService extends CommonService<VehicleSeatEntity, Long> {

    @Autowired
    VehicleSeatRepository vehicleSeatRepository;


    public TransactionalVehicleSeatService(CommonRepository<VehicleSeatEntity, Long> repository) {
        super(repository);
    }
}
