package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Component
public class TicketClient {

    @Autowired
    @Qualifier("databaseJourneyService")
    private  JourneyService journeyService;

    @Autowired
    @Qualifier("transactionalJourneyService")
    private JourneyService transactionalJourneyService;

    @Autowired
    @Qualifier("transactionalStopService")
    private StopService transactionalStopService;

    public Long createJourney(final JourneyEntity journeyEntity){
        return transactionalJourneyService.createJourney(journeyEntity);
    }

    public Long createStop(final StopEntity stopEntity){
        return transactionalStopService.createStop(stopEntity);
    }

    public Collection<Journey> find (String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo){
        //todo: check input param
        return  journeyService.find(stationFrom, stationTo, dateFrom,dateTo);
    }

    public Collection<Journey> find (String stationFrom, String stationTo){
        //todo: check input param
        return  journeyService.find(stationFrom, stationTo);
    }



}
