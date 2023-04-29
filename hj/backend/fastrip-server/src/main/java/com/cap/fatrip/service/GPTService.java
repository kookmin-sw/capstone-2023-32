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
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GPTService {

	private static RestTemplate restTemplate = new RestTemplate();
	private final WebClient client = WebClient.create();

	public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
		headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
		return new HttpEntity<>(requestDto, headers);
	}

	public ChatGptResponseDto getResponse(ChatGptRequestDto requestDto) {
//		ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
//				ChatGptConfig.URL,
//				chatGptRequestDtoHttpEntity,
//				ChatGptResponseDto.class);
//
//		ChatGptResponseDto body = responseEntity.getBody();
//		return body;


//		ChatGptResponseDto block = client.post()         // POST method
//				.uri(ChatGptConfig.URL)    // baseUrl 이후 uri
//				.bodyValue(chatGptRequestDtoHttpEntity)     // set body value
//				.retrieve()                 // client message 전송
//				.bodyToMono(ChatGptResponseDto.class)  // body type : EmpInfo
//				.block();
//		return block;

		WebClient client = WebClient.builder()
//				.baseUrl("http://localhost:5011")
				.filter(
						(req, next) -> next.exchange(
								ClientRequest.from(req).header(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY).build()
						)
				)
				.build();
		ChatGptResponseDto block = client.post()         // POST method
				.uri(ChatGptConfig.URL)    // baseUrl 이후 uri
				.bodyValue(requestDto)     // set body value
				.retrieve()                 // client message 전송
				.bodyToMono(ChatGptResponseDto.class)  // body type : EmpInfo
				.block();
		return block;
	}

	public ChatGptResponseDto askQuestion(String question) {
		return this.getResponse(
						new ChatGptRequestDto(
								ChatGptConfig.MODEL,
								question,
								ChatGptConfig.MAX_TOKEN,
								ChatGptConfig.TEMPERATURE,
								ChatGptConfig.TOP_P
						)
		);
	}
}
