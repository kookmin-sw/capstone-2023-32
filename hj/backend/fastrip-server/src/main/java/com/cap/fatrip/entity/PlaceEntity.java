package com.cap.fatrip.entity;
import com.cap.fatrip.dto.PlaceDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "place")
public class PlaceEntity {
    @Id
    @Column(name="p_no")
    private long p_no;
    @Column(name="p_name")
    private String p_name;
    @Column(name="p_post")
    private String p_post;
    @Column(name="p_locate")
    private String p_locate;
    @Column(name="p_country")
    private String p_country;
    @Column(name="p_region")
    private String p_region;

    public static PlaceEntity toPlaceEntity(PlaceDto placeDto){
        PlaceEntity placeEntity = new PlaceEntity();
        placeEntity.p_no = placeDto.getP_no();
        placeEntity.p_name = placeDto.getP_name();
        placeEntity.p_post = placeDto.getP_post();
        placeEntity.p_locate = placeDto.getP_locate();
        placeEntity.p_country = placeDto.getP_country();
        placeEntity.p_region = placeDto.getP_region();
        return placeEntity;
    }


}
