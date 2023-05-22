package com.cap.fatrip.service;

import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.dto.inbound.PlanReqDto;
import com.cap.fatrip.dto.inbound.PlanSaveDto;
import com.cap.fatrip.dto.inbound.PlanUpdateDto;
import com.cap.fatrip.dto.outbound.PlanResDto;
import com.cap.fatrip.entity.*;
import com.cap.fatrip.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlanService {
	private final PlanRepository planRepository;
	private final PPlanRepository pplanRepository;
	private final UserRepository userRepository;
	private final PlanTagRepository planTagRepository;
	private final TagRepository tagRepository;
	private final LikeRepository likeRepository;

	public List<PPlanEntity> savePplans(List<PPlanEntity> planEntityList) {
		return pplanRepository.saveAll(planEntityList);
	}

	public List<PlanTagEntity> associateTags(PlanEntity planEntity, List<TagEntity> tagEntities) {
		List<PlanTagEntity> planTagEntityList = tagEntities.stream().map(tagEntity -> new PlanTagEntity(planEntity, tagEntity)).toList();
		return planTagRepository.saveAll(planTagEntityList);
	}

	public PlanEntity savePlan(PlanSaveDto planDto) {
		PlanEntity planEntity = PlanEntity.ofForSave(planDto);
		UserEntity userEntity = userRepository.findById(planDto.getUserId()).orElseThrow(() -> {
			log.error("there's no such user id : {}", planDto.getUserId());
			return new NoSuchElementException("there's no user");
		});
		planEntity.setUser(userEntity);
		return planRepository.save(planEntity);
	}

	public PlanEntity updatePlan(PlanUpdateDto planDto) throws Exception {
		UserEntity userEntity = userRepository.findById(planDto.getUserId()).orElseThrow(() -> {
			log.error("there's no such user id : {}", planDto.getUserId());
			return new NoSuchElementException("there's no user");
		});
		UserDto userDto = UserService.getUserFromAuth();
		if (!userEntity.getId().equals(userDto.getId())) {
			log.info("{}({})는 {}({})에 접근 권한이 없습니다.", userDto.getNickname(), userDto.getId(), planDto.getTitle(), planDto.getPlanId());
			throw new Exception("user id is not the same as post id");
		}
		Optional<PlanEntity> planEntity = planRepository.findById(planDto.getPlanId());

		if (planEntity.isPresent()) {
			PlanEntity p = planEntity.get();
			p.setTitle(planDto.getTitle());
			p.setComment(planDto.getComment());
			p.setImage(planDto.getImage());
			return planRepository.save(p);
		} else {
			throw getPlanException(planDto.getPlanId());
		}

	}

	public List<PlanResDto> getPlans(PlanReqDto planReqDto) {
		String title = planReqDto.getTitle() == null ? "" : planReqDto.getTitle();
		List<String> tags = planReqDto.getTags() == null ? new ArrayList<>() : planReqDto.getTags();
		List<PlanEntity> planByTagsAndTitle = switch (tags.size()) {
			case 0 -> planRepository.findPlanByTagsAndTitle(title);
			case 1 -> planRepository.findPlanByTagsAndTitle(title, tags.get(0));
			case 2 -> planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1));
			case 3 -> planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2));
			case 4 -> planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3));
			case 5 ->
					planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3), tags.get(4));
			case 6 ->
					planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3), tags.get(4), tags.get(5));
			case 7 ->
					planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3), tags.get(4), tags.get(5), tags.get(6));
			case 8 ->
					planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3), tags.get(4), tags.get(5), tags.get(6), tags.get(7));
			case 9 ->
					planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3), tags.get(4), tags.get(5), tags.get(6), tags.get(7), tags.get(8));
			case 10 ->
					planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3), tags.get(4), tags.get(5), tags.get(6), tags.get(7), tags.get(8), tags.get(9));
			default -> new ArrayList<>();
		};
		return setLiked(planByTagsAndTitle);
	}

	private List<PlanResDto> setLiked(List<PlanEntity> planEntityList) {
		List<PlanResDto> planResDtos = new ArrayList<>();
		boolean login = UserService.isLogin();
		if (!login) {
			for (PlanEntity planEntity : planEntityList) {
				planResDtos.add(PlanResDto.of(planEntity));
			}
			return planResDtos;
		}
		UserDto userDto = UserService.getUserFromAuth();
		String userId = userDto.getId();
		UserEntity userEntity = userRepository.findById(userId).orElseThrow();
		for (PlanEntity planEntity : planEntityList) {
			PlanResDto dto = PlanResDto.of(planEntity);
			Optional<LikeEntity> optionalLike = likeRepository.findByPlanAndUser(planEntity, userEntity);
			if (optionalLike.isPresent()) {
				dto.setLiked(true);
			}
			planResDtos.add(dto);
		}
		return planResDtos;
	}

	public List<PlanResDto> getPlansByUser(String id) {

		UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> {
			log.error("there's no such user id : {}", id);
			return new NoSuchElementException("there's no user");
		});
		List<PlanEntity> planByUser = planRepository.findAllByUser(userEntity);
		return setLiked(planByUser);
	}

	public PlanEntity getPlanDetail(String planId) throws Exception {
		return planRepository.findById(planId).orElseThrow(() -> getPlanException(planId));
	}

	public void deletePlan(String id, String userId) throws Exception {
		PlanEntity planEntity = planRepository.findById(id).orElseThrow(() -> getPlanException(id));
		if (planEntity.getUser().getId().equals(userId)) {
			planRepository.deleteById(id);
		} else {
			throw new Exception("해당 유저는 이 계획의 소유자가 아닙니다.");
		}
	}

	public void updatePplans(PlanEntity planEntity, List<PPlanDto> pPlanDtos) {
		pplanRepository.deleteAllByPlan(planEntity);
		List<PPlanEntity> pPlanEntityList = pPlanDtos.stream().map(planDto -> {
			PPlanEntity pPlan = PPlanEntity.of(planDto);
			pPlan.setPlan(planEntity);
			return pPlan;
		}).toList();
		pplanRepository.saveAll(pPlanEntityList);
	}

	public Exception getPlanException(String planId) {
		log.error("there's no such plan id : {}", planId);
		return new NoSuchElementException("there's no plan");
	}

	public void updateTags(PlanEntity planEntity, PlanUpdateDto planDto) {
		Set<String> oldTags = new HashSet<>(planDto.getOldTags());
		Set<String> newTags = new HashSet<>(planDto.getTags());
		Set<String> sameTags = new HashSet<>(planDto.getOldTags());
		sameTags.retainAll(newTags);
		oldTags.removeAll(sameTags);
		newTags.removeAll(sameTags);

		List<TagEntity> updateTagList = new ArrayList<>();
		List<TagEntity> tagsForPt = new ArrayList<>();
		for (String oldTag : oldTags) {
			TagEntity tagEntity = tagRepository.findByName(oldTag).get();
			tagEntity.setCount(tagEntity.getCount() - 1);
			updateTagList.add(tagEntity);
		}
		for (String newTag : newTags) {
			TagEntity tag = TagEntity.builder().name(newTag).build();
			updateTagList.add(tag);
			tagsForPt.add(tag);
		}
		for (String sameTag : sameTags) {
			TagEntity tagEntity = tagRepository.findByName(sameTag).get();
			updateTagList.add(tagEntity);
			tagsForPt.add(tagEntity);
		}
		tagRepository.saveAll(updateTagList);
		List<PlanTagEntity> planTagEntities = tagsForPt.stream().map(tag -> {
			PlanTagEntity temp = new PlanTagEntity();
			temp.setPlan(planEntity);
			temp.setTag(tag);
			return temp;
		}).toList();
		planTagRepository.deleteAllByPlan(planEntity);
		planTagRepository.saveAll(planTagEntities);
	}
}
