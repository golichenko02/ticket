package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
public class VehicleEntity extends AbstractModifyEntity<Long> {

    @Embedded
    private CommonInfo commonInfo;

    @OneToMany(mappedBy = "vehicle")
    private List<JourneyEntity> journeys = new ArrayList<>();

    public void addJourney(final JourneyEntity journeyEntity) {
        if(Objects.isNull(journeyEntity)) throw new IllegalArgumentException("journeyEntity must be set");
        if (journeys == null) {
            journeys = new ArrayList<>();
        }
        this.journeys.add(journeyEntity);
        journeyEntity.setVehicle(this);
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
}
