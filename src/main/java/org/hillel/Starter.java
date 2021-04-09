package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.CommonInfo;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopAdditionalInfoEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.service.DatabaseJourneyServiceImpl;
import org.hillel.service.JourneyService;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {


        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        JourneyEntity journeyEntity = new JourneyEntity("Kharkiv", "Dnipro",
                LocalDate.parse("2021-12-21"),
                LocalDate.parse("2021-12-20"));


        System.out.println("Create journey with id = " + ticketClient.createJourney(journeyEntity));

        StopAdditionalInfoEntity stopAdditionalInfoEntity = new StopAdditionalInfoEntity();
        stopAdditionalInfoEntity.setLatitude(10D);
        stopAdditionalInfoEntity.setLongitude(176D);
        StopEntity stopEntity = new StopEntity();

        stopEntity.addStopAdditionalInfo(stopAdditionalInfoEntity);
        CommonInfo commonInfo = new CommonInfo();
        commonInfo.setName("stop 1");
        commonInfo.setDescription("stop 1 description");
        stopEntity.setCommonInfo(commonInfo);

        stopEntity.setApplyToJourneyBuild(stopEntity.isActive());

        ticketClient.createStop(stopEntity);

//        System.out.println("route Kyiv -> Lviv on a specific day");
//        System.out.println(ticketClient.find("Kharkiv", "Dnipro", LocalDate.parse("2021-12-20"),
//                LocalDate.parse("2021-12-21")));
//
//        System.out.println("All possible dates of route Kharkiv -> Dnipro");
//        System.out.println(ticketClient.find("Kharkiv", "Dnipro"));

    }
}
