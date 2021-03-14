package org.hillel.config;

import org.hillel.service.InMemoryJourneyServiceImpl;
import org.hillel.service.JourneyService;
import org.hillel.service.StubJourneyServiceImpl;
import org.hillel.service.TicketClient;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("org.hillel.service")
public class RootConfig {

    @Bean
    public TicketClient ticketClient(){
        return new TicketClient(inMemoryJourneyService());
    }

    @Bean("inMemoryJourneyService")
    @Lazy
    public JourneyService inMemoryJourneyService(){
        System.out.println("getInMemoryROOTCONFIG");
        return new InMemoryJourneyServiceImpl();
    }
    @Bean
    public JourneyService stubService(){
        return new StubJourneyServiceImpl();
    }


}
