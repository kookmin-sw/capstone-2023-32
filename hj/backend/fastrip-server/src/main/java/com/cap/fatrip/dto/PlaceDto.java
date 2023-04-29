package com.cap.fatrip.dto;
import com.cap.fatrip.entity.PlaceEntity;
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
public class PlaceDto {
    private long p_no;
    private String p_name;
    private String p_post;
    private String p_locate;
    private String p_country;
    private String p_region;
    public static PlaceDto of(PlaceEntity placeEntity){
        PlaceDto place = new PlaceDto();
        place.p_no = placeEntity.getP_no();
        place.p_name = placeEntity.getP_name();
        place.p_post = placeEntity.getP_post();
        place.p_locate = placeEntity.getP_locate();
        place.p_country = placeEntity.getP_country();
        place.p_region = placeEntity.getP_region();
        return place;
    }
}
