package com.cap.fatrip.dto.inbound;

import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.dto.PlaceDto;
import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.UserEntity;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class savePlanDto {
    private List<PPlanDto> pplan;

    private List<PlaceDto> place;

    private PlanDto plan;
}
