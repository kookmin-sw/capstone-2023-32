package com.cap.fatrip.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Choice implements Serializable {
	private String text;
	private Integer index;
	@JsonProperty("finish_reason")
	private String finishReason;
}