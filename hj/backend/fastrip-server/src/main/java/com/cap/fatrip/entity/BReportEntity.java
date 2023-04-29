package com.cap.fatrip.entity;

import com.cap.fatrip.dto.BReportDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "breport")
public class BReportEntity {
    @Id
    @OneToMany
    @JoinColumn(name="b_key")
    private BoardEntity board;

    @OneToOne
    @JoinColumn(name="u_key")
    private UserEntity user;

    @Column(name="br_reason")
    private String br_reason;

    public static BReportEntity toBReportEntity(BReportDto breportDto){
        BReportEntity breportEntity = new BReportEntity();

        breportEntity.user =breportDto.getUser();
        breportEntity.board = breportDto.getBoard();
        breportEntity.br_reason = breportDto.getBr_reaseon();

        return breportEntity;
    }
}
