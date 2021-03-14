package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.context.AppContext;
import org.hillel.service.JourneyService;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
//        AppContext.load("application.properties");

        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");
//        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        System.out.println("after init");

        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);
        System.out.println(ticketClient.find("Kiev", "Odessa", LocalDate.now(), LocalDate.now().plusDays(1)));

//        ticketClient = applicationContext.getBean(TicketClient.class);
//        System.out.println(ticketClient.find("Lviv", "Kiev", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)));
    }
}
