package com.cap.fatrip.entity;

import com.cap.fatrip.dto.KbnDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "kbn")
public class KbnEntity {
    @Id
    @Column(name="kbn_id")
    private long kbn_id;
    @Column(name="kbn")
    private String kbn;

    public static KbnEntity toKbnEntity(KbnDto kbnDto){
        KbnEntity kbnEntity = new KbnEntity();
        kbnEntity.kbn_id = kbnDto.getKbn_id();
        kbnEntity.kbn = kbnDto.getKbn();
        return kbnEntity;
    }
}
