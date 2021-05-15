package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleSeatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class TicketClient {

    @Autowired
    @Qualifier("transactionalJourneyService")
    private GenericService transactionalJourneyService;

    @Autowired
    @Qualifier("transactionalStopService")
    private GenericService transactionalStopService;

    @Autowired
    @Qualifier("transactionalVehicleService")
    private GenericService transactionalVehicleService;

    @Autowired
    @Qualifier("transactionalVehicleSeatService")
    private GenericService transactionalVehicleSeatService;

    public JourneyEntity createOrUpdateJourney(final JourneyEntity journeyEntity) {
        return (JourneyEntity) transactionalJourneyService.createOrUpdate(journeyEntity);
    }

    public Optional<JourneyEntity> findJourneyById(Long id, boolean withDependencies) {
//        Assert.notNull(id, "id must be set");
        return id == null ? Optional.empty() : transactionalJourneyService.findById(id, withDependencies);
    }

    public StopEntity createOrUpdateStop(final StopEntity stopEntity) {
        return (StopEntity) transactionalStopService.createOrUpdate(stopEntity);
    }

    public VehicleEntity createOrUpdateVehicle(final VehicleEntity vehicleEntity) {
        return (VehicleEntity) transactionalVehicleService.createOrUpdate(vehicleEntity);
    }

    public VehicleSeatEntity createOrUpdateSeat(final VehicleSeatEntity vehicleSeatEntity) {
        return (VehicleSeatEntity) transactionalVehicleSeatService.createOrUpdate(vehicleSeatEntity);
    }

    public void removeJourney(JourneyEntity journeyEntity) {
        transactionalJourneyService.remove(journeyEntity);
    }

    public void removeJourneyById(Long id) {
        transactionalJourneyService.removeById(id);
    }


    public void removeVehicle(VehicleEntity vehicleEntity) {
        transactionalVehicleService.remove(vehicleEntity);
    }

    public void removeVehicleById(Long id) {
        transactionalVehicleService.removeById(id);
    }

    public void removeStop(StopEntity stopEntity) {
        transactionalStopService.remove(stopEntity);
    }

    public void removeStopById(Long id) {
        transactionalStopService.removeById(id);
    }

    public Collection<VehicleEntity> findVehiclesByIds(Long... ids) {
        return transactionalVehicleService.findByIds(ids);
    }

    public Optional<VehicleEntity> findVehicleById(Long id) {
        return transactionalVehicleService.findById(id);
    }

    public Collection<VehicleEntity> findAllVehicles(QueryType queryType) {
        return transactionalVehicleService.findAll(queryType);
    }

    public Collection<JourneyEntity> findAllJourneys(QueryType queryType) {
        return transactionalJourneyService.findAll(queryType);
    }

    public Collection<StopEntity> findAllStops(QueryType queryType) {
        return transactionalStopService.findAll(queryType);
    }

    public Collection<VehicleSeatEntity> findAllSeats(QueryType queryType) {
        return transactionalVehicleSeatService.findAll(queryType);
    }

}
