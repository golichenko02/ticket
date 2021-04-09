package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hillel.persistence.entity.enums.DirectionType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "journey")
@NoArgsConstructor
@Getter
@Setter
public class JourneyEntity extends AbstractModifyEntity<Long> {


    @Column(name = "station_from")
    private String stationFrom;

    @Column(name = "station_to")
    private String stationTo;

    @Column(name = "departure")
    private LocalDate departure;

    @Column(name = "arrival")
    private LocalDate arrival;


    @Column(name = "route")
    @Enumerated(EnumType.STRING)
    private DirectionType direction = DirectionType.TO;

    public JourneyEntity(String stationFrom, String stationTo, LocalDate departure, LocalDate arrival) {
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.departure = departure;
        this.arrival = arrival;
    }
}
