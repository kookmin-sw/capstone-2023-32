package com.cap.fatrip.dto.inbound;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanSaveDto {
	private String userId;
	private String title;
	private List<String> tags;
	private String comment;
	private String image;
	private String category;
}
