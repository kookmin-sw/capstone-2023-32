package com.cap.fatrip.dto;
import com.cap.fatrip.entity.BScrapEntity;
import com.cap.fatrip.entity.BoardEntity;
import com.cap.fatrip.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BScrapDto {
    private UserEntity user;
    private BoardEntity board;

    public static BScrapDto of(BScrapEntity bscrapEntity){
        BScrapDto bscrap = new BScrapDto();

        bscrap.user = bscrapEntity.getUser();
        bscrap.board = bscrapEntity.getBoard();
        return bscrap;
    }

}
