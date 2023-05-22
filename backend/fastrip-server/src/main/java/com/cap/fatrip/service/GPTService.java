package com.cap.fatrip.service;

import com.cap.fatrip.config.ChatGptConfig;
import com.cap.fatrip.dto.ChatGptRequestDto;
import com.cap.fatrip.dto.ChatGptResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class GPTService {
	private WebClient client;

	@PostConstruct
	private void init() {
		client = WebClient.builder()
				.baseUrl(ChatGptConfig.BASE_URL)
				.defaultHeaders(httpHeaders -> httpHeaders.addAll(setHeaders()))
				.build();

		// only for dev
		setClientForDev();
	}

	private HttpHeaders setHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(ChatGptConfig.API_KEY);
		headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
		return headers;
	}

	private void setClientForDev() {
		ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
//				.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024*1024*50))
				.build();
		exchangeStrategies
				.messageWriters().stream()
				.filter(LoggingCodecSupport.class::isInstance)
				.forEach(writer -> ((LoggingCodecSupport) writer).setEnableLoggingRequestDetails(true));

		client = client.mutate()
				.exchangeStrategies(exchangeStrategies)
				.filter(ExchangeFilterFunction.ofRequestProcessor(
						clientRequest -> {
							log.debug("Request: {} {}", clientRequest.method(), clientRequest.url());
							clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
							return Mono.just(clientRequest);
						}
				))
				.filter(ExchangeFilterFunction.ofResponseProcessor(
						clientResponse -> {
							clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
							return Mono.just(clientResponse);
						}
				))
				.build();
	}

	public ChatGptResponseDto getResponse(ChatGptRequestDto requestDto) {
		return client.post()         // POST method
				.uri(ChatGptConfig.URI)    // baseUrl 이후 uri
				.bodyValue(requestDto)     // set body value
				.retrieve()                 // client message 전송
				.bodyToMono(ChatGptResponseDto.class)  // body type : EmpInfo
				.block();
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
