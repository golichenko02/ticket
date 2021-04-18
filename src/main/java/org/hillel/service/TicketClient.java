package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
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

    public Long createJourney(final JourneyEntity journeyEntity) {
        return transactionalJourneyService.createJourney(journeyEntity);
    }

    public Optional<JourneyEntity> getJourneyById(Long id, boolean withDependencies) {
//        Assert.notNull(id, "id must be set");
        return id == null ? Optional.empty() : transactionalJourneyService.getById(id, withDependencies);
    }

    public Long createStop(final StopEntity stopEntity) {
        return transactionalStopService.createStop(stopEntity);
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        //todo: check input param
        return databaseJourneyService.find(stationFrom, stationTo, dateFrom, dateTo);
    }

    public Collection<Journey> find(String stationFrom, String stationTo) {
        //todo: check input param
        return databaseJourneyService.find(stationFrom, stationTo);
    }


    public void saveJourney(JourneyEntity journey) {
        transactionalJourneyService.save(journey);
    }
}
