package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hillel.persistence.entity.enums.DirectionType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "journey")
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class JourneyEntity extends AbstractModifyEntity<Long> {


    @Column(name = "station_from", nullable = false, length = 50)
    private String stationFrom;

    @Column(name = "station_to", nullable = false, length = 50)
    private String stationTo;

    @Column(name = "departure", nullable = false)
    private LocalDate departure;

    @Column(name = "arrival", nullable = false)
    private LocalDate arrival;


    @Column(name = "direction", length = 30)
    @Enumerated(EnumType.STRING)
    private DirectionType direction = DirectionType.TO;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "journey_stop", indexes = @Index(name = "journey_stop_idx", columnList = "journey_id, stop_id"),
            joinColumns = @JoinColumn(name = "journey_id"),
            inverseJoinColumns = @JoinColumn(name = "stop_id")
    )
    private List<StopEntity> stops = new ArrayList<>();


    public void addStop(final StopEntity stop) {
        if (Objects.isNull(stop)) throw new IllegalArgumentException("StopEntity must be set");
        if (stops == null)
            stops = new ArrayList<>();
        this.stops.add(stop);
        stop.addJourney(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JourneyEntity)) return false;
        JourneyEntity journey = (JourneyEntity) o;
        return getId() != null && Objects.equals(journey.getId(), getId());
    }



    @Override
    public String toString() {
        return new StringJoiner(", ", JourneyEntity.class.getSimpleName() + "[", "]")
                .add("stationFrom='" + stationFrom + "'")
                .add("stationTo='" + stationTo + "'")
                .add("departure=" + departure)
                .add("arrival=" + arrival)
                .add("direction=" + direction)
                .add("vehicle=" + vehicle)
                .toString();
    }
}
