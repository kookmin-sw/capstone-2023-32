package com.cap.fatrip.controller;

import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.dto.inbound.*;
import com.cap.fatrip.dto.outbound.PlanDetailDto;
import com.cap.fatrip.dto.outbound.PlanResDto;
import com.cap.fatrip.entity.*;
import com.cap.fatrip.repository.LikeRepository;
import com.cap.fatrip.repository.UserRepository;
import com.cap.fatrip.service.PlanService;
import com.cap.fatrip.service.TagService;
import com.cap.fatrip.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
@RequestMapping("/api/plan")
@RequiredArgsConstructor
public class PlanController {
	private final PlanService planService;
	private final TagService tagService;
	private final LikeRepository likeRepository;
	private final UserRepository userRepository;

	@PostMapping("/list")
	public List<PlanResDto> findAll(@RequestBody PlanReqDto planReqDto) {
		return planService.getPlans(planReqDto);
	}

	@GetMapping("/list")
	public List<PlanResDto> findAllByUser(@RequestParam("userId") String userId) throws Exception {
		return planService.getPlansByUser(userId);
	}

	@GetMapping("/myList")
	public List<PlanResDto> findAll() throws Exception {
		UserDto userDto = UserService.getUserFromAuth();
		String id = userDto.getId();
		return planService.getPlansByUser(id);
	}

	@GetMapping(value = "/like", params = "planId")
	@Transactional
	public PlanDetailDto toggleLike(@RequestParam("planId") String planId) throws Exception {
		// 로그인 됐는지 확인
			// 안 됐으면 그대로 return
		UserDto userDto = UserService.getUserFromAuth();
		// 됐으면 해당 게시물 좋아요 눌렸는지 확인
		PlanEntity planEntity = planService.getPlanDetail(planId);
		UserEntity userEntity = userRepository.findById(userDto.getId()).orElseThrow();
		Optional<LikeEntity> optionalLike = likeRepository.findByPlanAndUser(planEntity, userEntity);
		if (optionalLike.isPresent()) {
			// 눌렸으면 취소(삭제)
			LikeEntity likeEntity = optionalLike.get();
			likeRepository.delete(likeEntity);
			planService.setPlanLike(planEntity, -1);
		} else {
			// 안 눌렸으면 추가
			LikeEntity likeEntity = new LikeEntity();
			likeEntity.setUser(userEntity);
			likeEntity.setPlan(planEntity);
			likeRepository.save(likeEntity);
			planService.setPlanLike(planEntity, 1);
		}
		return getPlanDetail(planEntity.getId());
	}

	@GetMapping(params = {"id"})
	public PlanDetailDto getPlanDetail(@RequestParam String id) throws Exception {
		PlanDetailDto planDetailDto = new PlanDetailDto();
		PlanEntity planDetail = planService.getPlanDetail(id);
		PlanDto planDto = PlanDto.of(planDetail);

		boolean login = UserService.isLogin();
		UserDto userDto;
		if (login) {
			userDto = UserService.getUserFromAuth();
			UserEntity userEntity = userRepository.findById(userDto.getId()).orElseThrow();
			if (likeRepository.findByPlanAndUser(planDetail, userEntity).isPresent()) {
				planDto.setLiked(true);
			}
		}

		planDetailDto.setPlan(planDto);
		planDetailDto.setPplan(planDetail.getPPlanEntities().stream().map(PPlanDto::of).toList());
		planDetailDto.getPlan().setTags(planDetail.getPlanTagEntities().stream().map(planTagEntity -> planTagEntity.getTag().getName()).toList());

		return planDetailDto;
	}

	@DeleteMapping(params = {"id"})
	public boolean deletePlan(@RequestParam String id) throws Exception {
		String userId = UserService.getUserFromAuth().getId();
		planService.deletePlan(id, userId);
		return true;
	}

	@PostMapping("/save")
	public void save(@RequestBody PlanDetailSaveDto saveDto) {
		// plan 저장
		PlanEntity planEntity = planService.savePlan(saveDto.getPlan());
		// pplan 저장
		List<PPlanEntity> pPlanEntities = saveDto.getPplan().stream().map(pPlanDto -> {
			PPlanEntity pPlanEntity = PPlanEntity.of(pPlanDto);
			pPlanEntity.setPlan(planEntity);
			return pPlanEntity;
		}).toList();
		List<PPlanEntity> pPlanEntityList = planService.savePplans(pPlanEntities);
		// tag 저장
		List<TagEntity> tagEntities = tagService.saveTags(saveDto.getPlan().getTags());

		// plan <--> tag 연관관계 저장.
		List<PlanTagEntity> planTagEntities = planService.associateTags(planEntity, tagEntities);
		// plan <--> pplan 연관관계 저장은 이미 위에서 함. n : m 이 아니기 때문에 추가적인 매핑 작업 불필요.

		log.info("save plan success");
	}

	@PutMapping("/update")
	@Transactional
	public void update(@RequestBody PlanDetailUpdateDto saveDto) throws Exception {
		PlanUpdateDto planDto = saveDto.getPlan();
		PlanEntity planEntity = planService.updatePlan(planDto);

		planService.updateTags(planEntity, planDto);

		planService.updatePplans(planEntity, saveDto.getPplan());

		log.info("update plan success.");
	}

}
