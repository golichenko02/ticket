package org.hillel.service;

import org.hillel.Journey;
import org.hillel.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//@Component("databaseJourneyService")
public class DatabaseJourneyServiceImpl implements JourneyService {

    private List<Journey> getJourney(String from, String to) throws SQLException {
        List<Journey> journeys = new ArrayList<>();
        Connection connection = DatabaseUtil.getConnection();
        ResultSet resultSet = connection
                .createStatement()
                .executeQuery(String.format("SELECT station_to , station_from, departure, arrival, route " +
                        "FROM tickets.public.journey " +
                        "WHERE station_to = '%s' AND station_from = '%s'", to, from));
        while (resultSet.next()) {
            journeys.add(new Journey(resultSet.getString("station_from"), resultSet.getString("station_to"),
                    LocalDate.parse(resultSet.getString("departure")), LocalDate.parse(resultSet.getString("arrival"))));
        }
        connection.close();

        return journeys.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(journeys);
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        List<Journey> out = new ArrayList<>();
        try {
            for (Journey item : getJourney(stationFrom, stationTo)) {
                if (item.getDepartureTime().equals(dateFrom) && item.getArrivalTime().equals(dateTo)) {
                    out.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(out);
    }


    // finds all possible dates of route
    @Override
    public Collection<Journey> find(String stationFrom, String stationTo) {
        List<Journey> out = new ArrayList<>();
        try {
            out.addAll(getJourney(stationFrom, stationTo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(out);
    }
}
