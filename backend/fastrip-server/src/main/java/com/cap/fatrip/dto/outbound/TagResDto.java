package com.cap.fatrip.dto.outbound;

import com.cap.fatrip.entity.TagEntity;
import lombok.Data;

@Data
public class TagResDto {
	private String name;
	private int count;

	public static TagResDto of(TagEntity tagEntity) {
		TagResDto tagResDto = new TagResDto();
		tagResDto.name = tagEntity.getName();
		tagResDto.count = tagEntity.getCount();
		return  tagResDto;
	}
}
