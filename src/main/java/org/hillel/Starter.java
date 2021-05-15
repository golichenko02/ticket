package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.*;
import org.hillel.persistence.entity.enums.DirectionType;
import org.hillel.service.QueryType;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.*;

public class Starter {
    public static void main(String[] args) {


        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        VehicleEntity train = buildVehicle(buildCommonInfo("Черноморец", "Скоростной фирменный поезд"),
                100, buildSeats(true, 1, 2, 3, 4, 5, 6, 50, 52, 60, 71));

        train = ticketClient.createOrUpdateVehicle(train);


        JourneyEntity journeyEntity = buildJourney("Одесса", "Киев", LocalDate.now(), LocalDate.now().plusDays(1));


        journeyEntity = ticketClient.createOrUpdateJourney(journeyEntity);

        journeyEntity.addStop(buildStop(45D, 78D, LocalDate.parse("1865-04-05"), "Одесса",
                buildCommonInfo("Одесса-Главная", "Описание Одесса-Главная")));

        journeyEntity.addStop(buildStop(189D, 361D, LocalDate.parse("1870-04-05"), "Киев",
                buildCommonInfo("Киев-Пасс.", "Описание Киев-Пасс.")));

        journeyEntity.addVehicle(train);

        journeyEntity = ticketClient.createOrUpdateJourney(journeyEntity);

        for (VehicleSeatEntity seat : buildSeats(false, 10, 11, 12, 20, 25, 30)) {
            train.addSeat(seat.setJourney(journeyEntity));
            journeyEntity.getSeats().add(seat);
        }
        ticketClient.createOrUpdateVehicle(train);


//        System.out.println(ticketClient.findVehiclesByIds(1L, 2L, 3L, 4L, 5L));
//        System.out.println(ticketClient.findVehicleById(1L));
        System.out.println("HQL");
        System.out.println(ticketClient.findAllVehicles(QueryType.HQL));
//        System.out.println(ticketClient.findAllJourneys(QueryType.HQL)); LazyInitializationException  из-за Vehicle
        System.out.println(ticketClient.findAllStops(QueryType.HQL));
        System.out.println(ticketClient.findAllSeats(QueryType.HQL));
        System.out.println("NATIVE");
        System.out.println(ticketClient.findAllVehicles(QueryType.NATIVE));
//        System.out.println(ticketClient.findAllJourneys(QueryType.NATIVE));
        System.out.println(ticketClient.findAllStops(QueryType.NATIVE));
        System.out.println(ticketClient.findAllSeats(QueryType.NATIVE));
        System.out.println("CRITERIA");
        System.out.println(ticketClient.findAllVehicles(QueryType.CRITERIA));
//        System.out.println(ticketClient.findAllJourneys(QueryType.CRITERIA)); LazyInitializationException  из-за Vehicle
        System.out.println(ticketClient.findAllStops(QueryType.CRITERIA));
        System.out.println(ticketClient.findAllSeats(QueryType.CRITERIA));
        System.out.println("NAMED");
        System.out.println(ticketClient.findAllVehicles(QueryType.NAMED));
//        System.out.println(ticketClient.findAllJourneys(QueryType.NAMED)); LazyInitializationException  из-за Vehicle
        System.out.println(ticketClient.findAllStops(QueryType.NAMED));
        System.out.println(ticketClient.findAllSeats(QueryType.NAMED));
        System.out.println("STORED PROCEDURE");
        System.out.println(ticketClient.findAllVehicles(QueryType.STORED_PROCEDURE));
//        System.out.println(ticketClient.findAllJourneys(QueryType.STORED_PROCEDURE)); LazyInitializationException  из-за Vehicle
        System.out.println(ticketClient.findAllStops(QueryType.STORED_PROCEDURE));
        System.out.println(ticketClient.findAllSeats(QueryType.STORED_PROCEDURE));
    }

    private static JourneyEntity buildJourney(final String from, final String to,
                                              final LocalDate departureDate, final LocalDate arrivalDate) {

        final JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom(from);
        journeyEntity.setStationTo(to);
        journeyEntity.setDeparture(departureDate);
        journeyEntity.setArrival(arrivalDate);
        journeyEntity.setDirection(DirectionType.TO);
        journeyEntity.setActive(true);
        return journeyEntity;
    }

    private static StopEntity buildStop(final Double latitude, final Double longitude, final LocalDate buildDate,
                                        final String city, final CommonInfo commonInfo) {
        final StopAdditionalInfoEntity stopAdditionalInfoEntity = new StopAdditionalInfoEntity();
        stopAdditionalInfoEntity.setLatitude(latitude);
        stopAdditionalInfoEntity.setLongitude(longitude);
        stopAdditionalInfoEntity.setBuildDate(buildDate);
        stopAdditionalInfoEntity.setCity(city);

        final StopEntity stopEntity = new StopEntity();
        stopEntity.setCommonInfo(commonInfo);
        stopEntity.setActive(true);
        stopEntity.addStopAdditionalInfo(stopAdditionalInfoEntity);
        return stopEntity;
    }

    private static VehicleEntity buildVehicle(final CommonInfo commonInfo, final int maxSeats, final List<VehicleSeatEntity> seats) {
        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setCommonInfo(commonInfo);
        vehicleEntity.setMaxSeats(maxSeats);
        vehicleEntity.setActive(true);
        for (VehicleSeatEntity seat : seats)
            vehicleEntity.addSeat(seat);
        return vehicleEntity;
    }

    private static List<VehicleSeatEntity> buildSeats(final boolean isBooked, final Integer... seatNumbers) {
        final List<VehicleSeatEntity> seats = new ArrayList<>();
        for (Integer seatNumber : seatNumbers) {
            seats.add(new VehicleSeatEntity().setSeatNumber(seatNumber).setBooked(isBooked));
        }
        return seats;
    }

    private static CommonInfo buildCommonInfo(final String name, final String description) {
        return new CommonInfo().setName(name).setDescription(description);
    }
}
