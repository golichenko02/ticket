package org.hillel.config;

import org.hillel.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.hillel")
public class RootConfig {

//    @Bean
//    public TicketClient ticketClient(){
//        return new TicketClient(dataBaseJourneyService());
//    }
//
//    @Bean("inMemoryJourneyService")
//    public JourneyService inMemoryJourneyService(){
//        return new InMemoryJourneyServiceImpl();
//    }
//    @Bean
//    public JourneyService stubService(){
//        return new StubJourneyServiceImpl();
//    }
//
//    @Bean
//    public JourneyService dataBaseJourneyService(){
//        return new DatabaseJourneyServiceImpl();
//    }
}
