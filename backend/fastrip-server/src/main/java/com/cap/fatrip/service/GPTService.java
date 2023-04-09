package com.cap.fatrip.service;

import com.cap.fatrip.config.ChatGptConfig;
import com.cap.fatrip.dto.ChatGptRequestDto;
import com.cap.fatrip.dto.ChatGptResponseDto;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GPTService {

	private static RestTemplate restTemplate = new RestTemplate();

	public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
		headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
		return new HttpEntity<>(requestDto, headers);
	}

	public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
		ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
				ChatGptConfig.URL,
				chatGptRequestDtoHttpEntity,
				ChatGptResponseDto.class);

		return responseEntity.getBody();
	}

	public ChatGptResponseDto askQuestion(String question) {
		return this.getResponse(
				this.buildHttpEntity(
						new ChatGptRequestDto(
								ChatGptConfig.MODEL,
								question,
								ChatGptConfig.MAX_TOKEN,
								ChatGptConfig.TEMPERATURE,
								ChatGptConfig.TOP_P
						)
				)
		);
	}
}
