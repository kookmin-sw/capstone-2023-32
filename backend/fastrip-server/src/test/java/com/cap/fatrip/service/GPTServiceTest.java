package com.cap.fatrip.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GPTServiceTest {
	@Autowired
	GPTService gptService;
	@Test
	public void sendQuestion() {
		String question = "how are you today?";
		System.out.println(gptService.askQuestion(question));
	}
}