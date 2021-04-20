package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.DynamicUpdate;
import org.hillel.persistence.entity.util.YesNoConverter;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_seat")
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@DynamicUpdate
@Check(constraints = "seat_number > 0")
public class VehicleSeatEntity extends AbstractModifyEntity<Long> {

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @Column(name = "booked")
    @Convert(converter = YesNoConverter.class)
    private boolean isBooked = false;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "journey_id")
    private JourneyEntity journey;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;


}

