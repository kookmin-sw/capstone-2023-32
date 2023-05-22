package com.cap.fatrip.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class ChatGptResponseDto implements Serializable {
	private String id;
	private String object;
	private LocalDate created;
	private String model;
	private List<Choice> choices;
}