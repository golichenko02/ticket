package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.*;
import org.hillel.persistence.entity.enums.DirectionType;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {


        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Kharkiv");
        journeyEntity.setStationTo("Dnipro");
        journeyEntity.setDeparture(LocalDate.now());
        journeyEntity.setArrival(LocalDate.now().plusDays(2));
        journeyEntity.setActive(false);

        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setCommonInfo(new CommonInfo().setName("bus1").setDescription("bus 1 description"));

        journeyEntity.setVehicle(vehicleEntity);


        StopAdditionalInfoEntity stopAdditionalInfoEntity = new StopAdditionalInfoEntity();
        stopAdditionalInfoEntity.setLatitude(10D);
        stopAdditionalInfoEntity.setLongitude(176D);
        StopEntity stopEntity = new StopEntity();

        stopEntity.addStopAdditionalInfo(stopAdditionalInfoEntity);
        stopEntity.setCommonInfo(new CommonInfo().setName("stop 1").setDescription("stop 1 description"));
        stopEntity.setApplyToJourneyBuild(stopEntity.isActive());

        journeyEntity.addStop(stopEntity);

        ticketClient.createJourney(journeyEntity);

        final Optional<JourneyEntity> journeyById = ticketClient.getJourneyById(journeyEntity.getId(), true);
        final JourneyEntity journey = journeyById.get();
        System.out.println("get all stops by journey " + journey.getStops());
        System.out.println("create journey with id: " + journeyById);

        journey.setDirection(DirectionType.FROM);

        System.out.println("save journey");
        ticketClient.saveJourney(journey);

    }
}
