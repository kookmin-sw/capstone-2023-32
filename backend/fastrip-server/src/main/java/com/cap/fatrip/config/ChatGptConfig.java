package com.cap.fatrip.config;

public class ChatGptConfig {
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer ";
	public static final String API_KEY = "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";
	public static final String MODEL = "text-davinci-003";
	public static final Integer MAX_TOKEN = 300;
	public static final Double TEMPERATURE = 0.6;  // 0~1. 1에 가까울수록 무작위.
	public static final Double TOP_P = 0.2;  // 0~1 1에 가까울수록 무작위. 0.1이면 상위 10%만 고려한다고 보면 쉽다.
	public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
	public static final String BASE_URL = "https://api.openai.com";
	public static final String URI = "/v1/completions";
}