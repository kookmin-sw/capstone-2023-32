package com.cap.fatrip.controller;

import com.cap.fatrip.dto.outbound.TagResDto;
import com.cap.fatrip.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {
	private final TagService tagService;
	@GetMapping("/popular")
	public List<TagResDto> getPopular() {
		return tagService.getPopularTags();
	}
}
