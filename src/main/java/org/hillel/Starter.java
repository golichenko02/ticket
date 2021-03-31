package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.JourneyEntity;
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


//        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        System.out.println("after init");

        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        JourneyEntity journeyEntity = new JourneyEntity("Kharkiv", "Dnipro",
                LocalDate.parse("2021-12-20"),
                LocalDate.parse("2021-12-21"));


        System.out.println("Create journey with id = " + ticketClient.createJourney(journeyEntity));

//        System.out.println("route Kyiv -> Lviv on a specific day");
        System.out.println(ticketClient.find("Kharkiv", "Dnipro", LocalDate.parse("2021-12-20"),
                LocalDate.parse("2021-12-21")));

        System.out.println("All possible dates of route Kharkiv -> Dnipro");
        System.out.println(ticketClient.find("Kharkiv", "Dnipro"));

    }
}
