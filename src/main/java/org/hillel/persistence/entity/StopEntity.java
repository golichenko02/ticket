package org.hillel.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Transient
    private boolean applyToJourneyBuild;

    public void addStopAdditionalInfo(StopAdditionalInfoEntity stopAdditionalInfo){
        if(stopAdditionalInfo == null){
            this.additionalInfo = null;
            return;
        }
        stopAdditionalInfo.setStopEntity(this);
        this.additionalInfo = stopAdditionalInfo;
    }
}

