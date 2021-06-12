package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.*;
import org.hillel.persistence.entity.enums.DirectionType;
import org.hillel.persistence.jpa.repository.specification.JourneySpecification;
import org.hillel.persistence.jpa.repository.specification.StopSpecification;
import org.hillel.persistence.jpa.repository.specification.VehicleSeatSpecification;
import org.hillel.persistence.jpa.repository.specification.VehicleSpecification;
import org.hillel.service.TicketClient;
import org.hillel.service.query_info.PaginationInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Starter {
    public static void main(String[] args) {


        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        VehicleEntity train = buildVehicle("Черноморец", 100, buildSeats(true, 1, 2, 3, 4, 5, 6, 50, 52, 60, 71));


        VehicleEntity plain = buildVehicle("Turkish Airlines", 80, buildSeats(false, 10, 20, 25, 28, 30, 70, 76));

        train = ticketClient.createOrUpdateVehicle(train);

        plain = ticketClient.createOrUpdateVehicle(plain);

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


        System.out.println("FIND ALL WITH SPECIFICATION");
        System.out.println(ticketClient.findAllVehiclesWithSpecification(new PaginationInfo(2, 3, "name", true, VehicleSpecification.BY_ONLY_ACTIVE, null)));
        System.out.println(ticketClient.findAllStopsWithSpecification(new PaginationInfo(1, 2, "active", true, StopSpecification.BY_NAME, "Киев-Пасс.")));
        System.out.println(ticketClient.findAllSeatsWithSpecification(new PaginationInfo(1, 3, "id", false, VehicleSeatSpecification.BY_ONLY_BOOKED, null)));
        System.out.println(ticketClient.findAllJourneysWithSpecification(new PaginationInfo(1, 2, "id", true, JourneySpecification.BY_STATION_FROM, "Одесса")));

        System.out.println("FIND ALL");
        System.out.println(ticketClient.findAllVehicles(new PaginationInfo(0, 2, "name", true)));
        System.out.println(ticketClient.findAllStops(new PaginationInfo(1, 2, "id", true)));
        System.out.println(ticketClient.findAllSeats(new PaginationInfo(2, 2, "active", false)));
        System.out.println(ticketClient.findAllJourneys(new PaginationInfo(3, 2, "id", false)));

        System.out.println("Транспортные средства с наименьшим количеством свободных мест");
        System.out.println(ticketClient.findVehicleWithMinFreeSeats());

        System.out.println("Транспортные средства с наибольшим количеством свободных мест");
        System.out.println(ticketClient.findVehicleWithMaxFreeSeats());


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

    private static VehicleEntity buildVehicle(final String name, final int maxSeats, final List<VehicleSeatEntity> seats) {
        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName(name);
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
