package com.cap.fatrip.dto;
import com.cap.fatrip.entity.KbnEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KbnDto {
    private long kbn_id;
    private String kbn;

    public static KbnDto of(KbnEntity kbnEntity){
        KbnDto kbn = new KbnDto();
        kbn.kbn_id = kbnEntity.getKbn_id();
        kbn.kbn = kbnEntity.getKbn();
        return kbn;
    }
}
