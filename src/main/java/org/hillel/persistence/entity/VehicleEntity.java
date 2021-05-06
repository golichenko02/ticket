package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.DynamicUpdate;
import org.hillel.persistence.entity.exceptions.TooMuchSeatsException;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@Check(constraints = "max_seats > 0")
@DynamicUpdate
public class VehicleEntity extends AbstractModifyEntity<Long> {

    @Embedded
    private CommonInfo commonInfo;

    @Column(name = "max_seats", nullable = false)
    private int maxSeats;

    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.PERSIST, CascadeType.MERGE}/*, orphanRemoval = true*/)
    private List<JourneyEntity> journeys = new ArrayList<>();

    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<VehicleSeatEntity> seats = new ArrayList<>();

    public void addJourney(final JourneyEntity journeyEntity) {
        if (Objects.isNull(journeyEntity)) throw new IllegalArgumentException("JourneyEntity must be set");
        if (journeys == null) {
            journeys = new ArrayList<>();
        }
        this.journeys.add(journeyEntity);
        journeyEntity.setVehicle(this);
        this.seats.forEach(seat -> {
            if (seat.getJourney() == null) {
                seat.setJourney(journeyEntity);
                journeyEntity.getSeats().add(seat);
            }
        });
    }

    @SneakyThrows
    public void addSeat(VehicleSeatEntity seat) {
        if (Objects.isNull(seat)) throw new IllegalArgumentException("VehicleSeatEntity must be set");
        if (seats == null)
            seats = new ArrayList<>();
        if (seats.size() == maxSeats)
            throw new TooMuchSeatsException("The maximum value of seats for " + commonInfo.getName() + " is " + maxSeats);
        this.seats.add(seat);
        seat.setVehicle(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleEntity entity = (VehicleEntity) o;
        return getId() != null && Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VehicleEntity.class.getSimpleName() + "[", "]")
                .add("commonInfo=" + commonInfo)
                .toString();
    }

    public void removeAllJourneyAndSeats(){
        if(CollectionUtils.isEmpty(journeys)) return;
        journeys.forEach(journeyEntity -> journeyEntity.setVehicle(null));
        seats.forEach(vehicleSeatEntity -> vehicleSeatEntity.setVehicle(null));
     }
}
