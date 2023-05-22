package com.cap.fatrip.dto.inbound;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanUpdateDto {
	private String planId;
	private String userId;
	private String title;
	private List<String> tags;
	private List<String> oldTags;
	private String comment;
	private String image;
	private String category;
}
