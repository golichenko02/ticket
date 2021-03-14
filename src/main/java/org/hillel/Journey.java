package org.hillel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Journey {
    private final String stationFrom;
    private final String stationTo;
    private final LocalDate departureTime;
    private final LocalDate arrivalTime;
    private final String route;

    public Journey(final String stationFrom, final String stationTo, final LocalDate departureTime, final LocalDate arrivalTime) {
        if (stationFrom == null) throw new IllegalArgumentException("station must be set");
        if (stationTo == null) throw new IllegalArgumentException("station must be set");
        if (departureTime == null) throw new IllegalArgumentException("departure time must be set");
        if (arrivalTime == null) throw new IllegalArgumentException("arrival time must be set");
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.route = stationFrom + " -> " + stationTo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return Objects.equals(stationFrom, journey.stationFrom) && Objects.equals(stationTo, journey.stationTo) && Objects.equals(departureTime, journey.departureTime) && Objects.equals(arrivalTime, journey.arrivalTime) && Objects.equals(route, journey.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationFrom, stationTo, departureTime, arrivalTime, route);
    }

    @Override
    public String toString() {
        return "Journey{" +
                "stationFrom='" + stationFrom + '\'' +
                ", stationTo='" + stationTo + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", route='" + route + '\'' +
                '}';
    }

    public String getStationFrom() {
        return stationFrom;
    }

    public String getStationTo() {
        return stationTo;
    }

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public String getRoute() {
        return route;
    }
}
