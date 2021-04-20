package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleSeatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Component
public class TicketClient {

    @Autowired
    @Qualifier("databaseJourneyService")
    private JourneyService databaseJourneyService;

    @Autowired
    @Qualifier("transactionalJourneyService")
    private JourneyService transactionalJourneyService;

    @Autowired
    @Qualifier("transactionalStopService")
    private StopService transactionalStopService;

    @Autowired
    @Qualifier("transactionalVehicleService")
    private VehicleService transactionalVehicleService;

    @Autowired
    @Qualifier("transactionalVehicleSeatService")
    private VehicleSeatService transactionalVehicleSeatService;

    public JourneyEntity createOrUpdateJourney(final JourneyEntity journeyEntity) {
        return transactionalJourneyService.createOrUpdateJourney(journeyEntity);
    }

    public Optional<JourneyEntity> findJourneyById(Long id, boolean withDependencies) {
//        Assert.notNull(id, "id must be set");
        return id == null ? Optional.empty() : transactionalJourneyService.findById(id, withDependencies);
    }

    public StopEntity createOrUpdateStop(final StopEntity stopEntity) {
        return transactionalStopService.createOrUpdateStop(stopEntity);
    }

    public VehicleEntity createOrUpdateVehicle(final VehicleEntity vehicleEntity) {
        return transactionalVehicleService.createOrUpdateVehicle(vehicleEntity);
    }

    public VehicleSeatEntity createOrUpdateSeat(final VehicleSeatEntity vehicleSeatEntity) {
        return transactionalVehicleSeatService.createOrUpdateSeat(vehicleSeatEntity);
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        //todo: check input param
        return databaseJourneyService.find(stationFrom, stationTo, dateFrom, dateTo);
    }

    public Collection<Journey> find(String stationFrom, String stationTo) {
        //todo: check input param
        return databaseJourneyService.find(stationFrom, stationTo);
    }


}
