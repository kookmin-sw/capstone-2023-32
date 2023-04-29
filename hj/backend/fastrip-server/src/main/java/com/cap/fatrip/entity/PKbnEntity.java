package com.cap.fatrip.entity;
import com.cap.fatrip.dto.PKbnDto;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "pkbn") //place 구분
public class PKbnEntity {
    @Id
    @ManyToOne
    @JoinColumn(name="kbn_id")
    private KbnEntity kbn;

    @OneToMany
    @JoinColumn(name="p_no")
    private PlaceEntity place;

    public static PKbnEntity toPKbnEntity(PKbnDto pkbnDto){
        PKbnEntity pkbnEntity = new PKbnEntity();
        pkbnEntity.kbn = pkbnDto.getKbn();
        pkbnEntity.place = pkbnDto.getPlace();
        return pkbnEntity;
    }
}
