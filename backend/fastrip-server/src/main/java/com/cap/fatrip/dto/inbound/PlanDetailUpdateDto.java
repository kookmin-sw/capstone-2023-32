package com.cap.fatrip.dto.inbound;

import com.cap.fatrip.dto.PPlanDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDetailUpdateDto {
    private List<PPlanDto> pplan;

    private PlanUpdateDto plan;
}
