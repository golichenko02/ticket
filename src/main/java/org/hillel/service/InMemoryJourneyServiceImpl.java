package org.hillel.service;

import org.hillel.Journey;

import java.time.LocalDate;
import java.util.*;

//@Component("inMemoryJourneyService")
public class InMemoryJourneyServiceImpl implements JourneyService {

    private Map<String, List<Journey>> storage = new HashMap<>();

    //    private final String id;
    public InMemoryJourneyServiceImpl() {
//      id = identify;
//        System.out.println("call constructor InMemoryJourneyServiceImpl");
    }

    {
        storage.put("Kiev->Odessa", createJourney("Kiev", "Odessa"));
        storage.put("Odessa->Lviv", createJourney("Odessa", "Lviv"));
        storage.put("Lviv->Kiev", createJourney("Lviv", "Kiev"));
    }

    private List<Journey> createJourney(String from, String to) {
        return Arrays.asList(
                new Journey(from, to, LocalDate.now(), LocalDate.now().plusDays(1)),
                new Journey(from, to, LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)),
                new Journey(from, to, LocalDate.now().plusDays(2), LocalDate.now().plusDays(3)));
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        if (storage == null || storage.isEmpty()) return Collections.emptyList();
        List<Journey> journeys = storage.get(stationFrom + "->" + stationTo);
        if (journeys == null || journeys.isEmpty()) return Collections.emptyList();
        List<Journey> out = new ArrayList<>();
        for (Journey item : journeys) {
            if (item.getDepartureTime().equals(dateFrom) && item.getArrivalTime().equals(dateTo)) {
                out.add(item);
            }
        }
        return Collections.unmodifiableList(out);
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo) {
        if (storage == null || storage.isEmpty()) return Collections.emptyList();
        List<Journey> journeys = storage.get(stationFrom + "->" + stationTo);
        if (journeys == null || journeys.isEmpty()) return Collections.emptyList();
        List<Journey> out = new ArrayList<>(journeys);

        return Collections.unmodifiableList(out);
    }
}
