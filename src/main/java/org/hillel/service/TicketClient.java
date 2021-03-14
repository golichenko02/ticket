package org.hillel.service;

import org.hillel.Journey;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Component
public class TicketClient {
    private  JourneyService journeyService;

    public TicketClient(@Qualifier("inMemoryJourneyService") JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    public Collection<Journey> find (String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo){
        //todo: check input param
        return  journeyService.find(stationFrom, stationTo, dateFrom,dateTo);
    }

}
