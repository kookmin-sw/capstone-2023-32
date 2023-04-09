package com.cap.fatrip.controller;

import com.cap.fatrip.dto.ChatGptResponseDto;
import com.cap.fatrip.service.GPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/gpt")
@RequiredArgsConstructor
public class TestGPTController {
	private final GPTService gptService;
	@GetMapping(value = "/query", params = {"query"})
	public ChatGptResponseDto query(@RequestParam String query) {
		return gptService.askQuestion(query);
	}
}
