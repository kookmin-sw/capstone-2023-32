package com.cap.fatrip.dto;

import com.cap.fatrip.entity.BReportEntity;
import com.cap.fatrip.entity.BoardEntity;
import com.cap.fatrip.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BReportDto {
    private UserEntity user;
    private BoardEntity board;
    private String br_reaseon;

    public static BReportDto of(BReportEntity breportEntity){
        BReportDto breport = new BReportDto();

        breport.user = breportEntity.getUser();
        breport.board = breportEntity.getBoard();
        breport.br_reaseon = breportEntity.getBr_reason();
        return breport;
    }
}
