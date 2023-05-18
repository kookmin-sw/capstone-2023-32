package com.cap.fatrip.dto.inbound;

import com.cap.fatrip.dto.PPlanDto;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDetailSaveDto {
    private List<PPlanDto> pplan;

    private PlanSaveDto plan;
}
