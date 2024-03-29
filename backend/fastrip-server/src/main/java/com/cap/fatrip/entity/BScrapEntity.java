package com.cap.fatrip.entity;

import com.cap.fatrip.dto.BScrapDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "bscrap")
public class BScrapEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name="b_key")
    private BoardEntity board;

    public static BScrapEntity toBReportEntity(BScrapDto bscrapDto){
        BScrapEntity bscrapEntity = new BScrapEntity();

        bscrapEntity.user =bscrapDto.getUser();
        bscrapEntity.board = bscrapDto.getBoard();

        return bscrapEntity;
    }
}
