package com.cap.fatrip.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@ToString
public class Choice implements Serializable {

	private String text;
	private Integer index;
	@JsonProperty("finish_reason")
	private String finishReason;

	@Builder
	public Choice(String text, Integer index, String finishReason) {
		this.text = text;
		this.index = index;
		this.finishReason = finishReason;
	}
}