package org.hillel.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stop")
@Getter
@Setter
@NoArgsConstructor
public class StopEntity extends AbstractModifyEntity<Long> {

    @Embedded
    private CommonInfo commonInfo;

    @OneToOne(mappedBy = "stopEntity", cascade = {CascadeType.PERSIST})
    private StopAdditionalInfoEntity additionalInfo;

    @ManyToMany(mappedBy = "stops")
    List<JourneyEntity> journeys = new ArrayList<>();

    @Transient
    private boolean applyToJourneyBuild;


    public void addStopAdditionalInfo(StopAdditionalInfoEntity stopAdditionalInfo) {
        if (stopAdditionalInfo == null) {
            this.additionalInfo = null;
            return;
        }
        stopAdditionalInfo.setStopEntity(this);
        this.additionalInfo = stopAdditionalInfo;
    }

    public void addJourney(JourneyEntity journey) {
        if (Objects.isNull(journey)) throw new IllegalArgumentException("StopEntity must be set");
        if (journeys == null) journeys = new ArrayList<>();
        this.journeys.add(journey);
//        journey.addStop(this);
    }

}

