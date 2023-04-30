package com.cap.fatrip.dto;
import com.cap.fatrip.entity.KbnEntity;
import com.cap.fatrip.entity.PKbnEntity;
import com.cap.fatrip.entity.PlaceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PKbnDto {
    private KbnEntity kbn;
    private PlaceEntity place;
    public static PKbnDto of(PKbnEntity pkbnEntity){
        PKbnDto pkbn = new PKbnDto();
        pkbn.kbn = pkbnEntity.getKbn();
        pkbn.place = pkbnEntity.getPlace();
        return pkbn;
    }
}
