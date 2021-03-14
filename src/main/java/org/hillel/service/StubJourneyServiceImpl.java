package org.hillel.service;

import org.hillel.Journey;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
//@Component
public class StubJourneyServiceImpl implements JourneyService {

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        return Collections.emptyList();
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo) {
        return Collections.emptyList();
    }
}
