package com.cap.fatrip.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ChatGptRequestDto implements Serializable {
	private String model;
	private String prompt;
	@JsonProperty("max_tokens")
	private Integer maxTokens;
	private Double temperature;
	@JsonProperty("top_p")
	private Double topP;
}