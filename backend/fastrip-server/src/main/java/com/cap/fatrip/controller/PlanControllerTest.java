package com.cap.fatrip.controller;

import com.cap.fatrip.auth.TokenConstants;
import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.dto.inbound.PlanReqDto;
import com.cap.fatrip.dto.inbound.savePlanDto;
import com.cap.fatrip.dto.outbound.LoginDto;
import com.cap.fatrip.dto.outbound.PlanResDto;
import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.PlanTagEntity;
import com.cap.fatrip.entity.TagEntity;
import com.cap.fatrip.repository.PlanTagRepository;
import com.cap.fatrip.repository.TagRepository;
import com.cap.fatrip.service.PPlanService;
import com.cap.fatrip.service.PlanService;
import com.cap.fatrip.util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/plan/dummy")
@RequiredArgsConstructor
public class PlanControllerTest {
	private final PlanService planService;
	private final PPlanService pplanService;
	private final TagRepository tagRepository;
	private final PlanTagRepository planTagRepository;

	//	@PostMapping("/all")
	@GetMapping("/get1")
//	public List<PlanResDto> find(@RequestBody PlanReqDto planReqDto) {
	public void find1(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(HttpStatus.OK.value());

		var writer = response.getWriter();
		writer.println("{\n" +
				"  \"plan\": {\n" +
				"    \"id\": \"123123\",\n" +
				"    \"user\": {\"id\": \"Seulgi\"},\n" +
				"    \"title\" : \"사랑과 낭만의 도시 Paris\",\n" +
				"    \"tags\": [\"계획\", \"프랑스\", \"유럽여행\", \"혼자\"],\n" +
				"    \"comment\" : \"최적의 동선으로 짠 코스입니다! 이 정도는 다녀야 어디가서 프랑스 가봤다고 할 수 있죠!\",\n" +
				"    \"image\" : \"https://images.unsplash.com/photo-1583265266785-aab9e443ee68?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1374&q=80\",\n" +
				"    \"likes\" : 0,\n" +
				"  },\n" +
				"  \"pplan\": [\n" +
				"    {\n" +
				"      \"day\": \"1\",\n" +
				"      \"p_seq\": \"1\",\n" +
				"      \"p_name\": \"라파예트 백화점\",\n" +
				"      \"p_post\": \"40 Bd Haussmann, 75009 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"1\",\n" +
				"      \"p_seq\": \"2\",\n" +
				"      \"p_name\": \"에투알 광장 개선문\",\n" +
				"      \"p_post\": \"Pl. Charles de Gaulle, 75008 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"1\",\n" +
				"      \"p_seq\": \"3\",\n" +
				"      \"p_name\": \"Angelina\",\n" +
				"      \"p_post\": \"226 Rue de Rivoli, 75001 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"1\",\n" +
				"      \"p_seq\": \"4\",\n" +
				"      \"p_name\": \"알렉산더 3세 다리\",\n" +
				"      \"p_post\": \"Pont Alexandre III, 75008 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"2\",\n" +
				"      \"p_seq\": \"1\",\n" +
				"      \"p_name\": \"La Main Noire\",\n" +
				"      \"p_post\": \"12 Rue Cavallotti, 75018 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"2\",\n" +
				"      \"p_seq\": \"2\",\n" +
				"      \"p_name\": \"샤크레퀘르 대성당\",\n" +
				"      \"p_post\": \"35 Rue du Chevalier de la Barre, 75018 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"2\",\n" +
				"      \"p_seq\": \"3\",\n" +
				"      \"p_name\": \"마르스 광장 (에펠탑)\",\n" +
				"      \"p_post\": \"2 All. Adrienne Lecouvreur, 75007 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"2\",\n" +
				"      \"p_seq\": \"4\",\n" +
				"      \"p_name\": \"오페라\",\n" +
				"      \"p_post\": \"Pl. de l'Opéra, 75009 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"3\",\n" +
				"      \"p_seq\": \"1\",\n" +
				"      \"p_name\": \"이랑\",\n" +
				"      \"p_post\": \"2 bis Rue Daunou, 75002 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"  ]\n" +
				"}");
		writer.flush();
	}

	@GetMapping("/get2")
//	public List<PlanResDto> find(@RequestBody PlanReqDto planReqDto) {
	public void find2(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(HttpStatus.OK.value());

		var writer = response.getWriter();
		writer.println("{\n" +
				"  \"plan\": {\n" +
				"    \"id\": \"1231231\",\n" +
				"    \"user\": {\"id\": \"borakai\"},\n" +
				"    \"title\" : \"휴양지하면 보라카이\",\n" +
				"    \"tags\": [\"계획\", \"보라카이\", \"휴가\", \"가족\"],\n" +
				"    \"comment\" : \"abcdef\",\n" +
				"    \"image\" : \"https://images.unsplash.com/photo-1553195029-754fbd369560?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1476&q=80\",\n" +
				"    \"likes\" : 0,\n" +
				"  },\n" +
				"  \"pplan\": [\n" +
				"    {\n" +
				"      \"day\": \"1\",\n" +
				"      \"p_seq\": \"1\",\n" +
				"      \"p_name\": \"라파예트 백화점\",\n" +
				"      \"p_post\": \"40 Bd Haussmann, 75009 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"1\",\n" +
				"      \"p_seq\": \"2\",\n" +
				"      \"p_name\": \"에투알 광장 개선문\",\n" +
				"      \"p_post\": \"Pl. Charles de Gaulle, 75008 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"1\",\n" +
				"      \"p_seq\": \"3\",\n" +
				"      \"p_name\": \"Angelina\",\n" +
				"      \"p_post\": \"226 Rue de Rivoli, 75001 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"1\",\n" +
				"      \"p_seq\": \"4\",\n" +
				"      \"p_name\": \"알렉산더 3세 다리\",\n" +
				"      \"p_post\": \"Pont Alexandre III, 75008 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"2\",\n" +
				"      \"p_seq\": \"1\",\n" +
				"      \"p_name\": \"La Main Noire\",\n" +
				"      \"p_post\": \"12 Rue Cavallotti, 75018 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"2\",\n" +
				"      \"p_seq\": \"2\",\n" +
				"      \"p_name\": \"샤크레퀘르 대성당\",\n" +
				"      \"p_post\": \"35 Rue du Chevalier de la Barre, 75018 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"2\",\n" +
				"      \"p_seq\": \"3\",\n" +
				"      \"p_name\": \"마르스 광장 (에펠탑)\",\n" +
				"      \"p_post\": \"2 All. Adrienne Lecouvreur, 75007 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"2\",\n" +
				"      \"p_seq\": \"4\",\n" +
				"      \"p_name\": \"오페라\",\n" +
				"      \"p_post\": \"Pl. de l'Opéra, 75009 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"3\",\n" +
				"      \"p_seq\": \"1\",\n" +
				"      \"p_name\": \"이랑\",\n" +
				"      \"p_post\": \"2 bis Rue Daunou, 75002 Paris, 프랑스\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"France\"\n" +
				"    },\n" +
				"  ]\n" +
				"}");
		writer.flush();
	}

	@GetMapping("/get3")
//	public List<PlanResDto> find(@RequestBody PlanReqDto planReqDto) {
	public void find3(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(HttpStatus.OK.value());

		var writer = response.getWriter();
		writer.println("{\n" +
				"  \"plan\": {\n" +
				"    \"id\": \"12312312\",\n" +
				"    \"user\": {\"id\": \"Tanziro\"},\n" +
				"    \"title\" : \"오사카 여행 모집합니다~!\",\n" +
				"    \"tags\": [\"모집\", \"일본\", \"오사카\", \"단체\"],\n" +
				"    \"comment\" : \"오픈카톡방 링크 https://open.kakao.trip 부담없이 들어와주세요!\",\n" +
				"    \"image\" : \"https://images.unsplash.com/photo-1590253230532-a67f6bc61c9e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1431&q=80\",\n" +
				"    \"likes\" : 0,\n" +
				"  },\n" +
				"  \"pplan\": [\n" +
				"    {\n" +
				"      \"day\": \"1\",\n" +
				"      \"p_seq\": \"1\",\n" +
				"      \"p_name\": \"도톤보리\",\n" +
				"      \"p_post\": \"1 Chome Dotonbori, Chuo Ward, Osaka, 542-0071 일본\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"Japan\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"1\",\n" +
				"      \"p_seq\": \"2\",\n" +
				"      \"p_name\": \"신사이바스지 상점가\",\n" +
				"      \"p_post\": \"2 Chome-2-22 Shinsaibashisuji, Chuo Ward, Osaka, 542-0085 일본\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"Japan\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"2\",\n" +
				"      \"p_seq\": \"1\",\n" +
				"      \"p_name\": \"시텐노지\",\n" +
				"      \"p_post\": \"1 Chome-11-18 Shitennoji, Tennoji Ward, Osaka, 543-0051 일본\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"Japan\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"2\",\n" +
				"      \"p_seq\": \"2\",\n" +
				"      \"p_name\": \"덴노지 동물원\",\n" +
				"      \"p_post\": \"1-108 Chausuyamacho, Tennoji Ward, Osaka, 543-0063 일본\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"Japan\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"2\",\n" +
				"      \"p_seq\": \"3\",\n" +
				"      \"p_name\": \"신세카이\",\n" +
				"      \"p_post\": \"일본 〒556-0002 Osaka, Naniwa Ward, Ebisuhigashi, 2 Chome−5−1 ニューマルコ\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"Japan\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"day\": \"3\",\n" +
				"      \"p_seq\": \"1\",\n" +
				"      \"p_name\": \"유니버설 스튜디 재팬\",\n" +
				"      \"p_post\": \"2 Chome-1-33 Sakurajima, Konohana Ward, Osaka, 554-0031 일본\",\n" +
				"      \"p_locate\": \"Location coordinates\",\n" +
				"      \"p_country\": \"Japan\"\n" +
				"    },\n" +
				"  ]\n" +
				"}");
		writer.flush();
	}

	@PostMapping("/all")
	public List<PlanResDto> findAll(@RequestBody PlanReqDto planReqDto) {
		return planService.getPlans(planReqDto);
	}


	private PlanResDto[] createDummyDtoList() {
		int cnt = 15;
		PlanResDto[] dtos = new PlanResDto[cnt];
		for (int i = 0; i < cnt; i++) {
			PlanResDto dummyDto = createDummyDto();
			dtos[i] = dummyDto;
		}
		return dtos;
	}

	@PostMapping("/save")
	public String save(@RequestBody savePlanDto saveDto) {

		planService.savePlan(saveDto.getPlan());
		List<TagEntity> tagEntityList = new ArrayList<>();
		List<String> tags = saveDto.getTag();
		for(String tag : tags) {
			//if 절로 해당 tag가 안에 내용이 있는지 확인용
			tagEntityList.add(TagEntity.builder()
					.name(tag).build());
		}
		tagRepository.saveAll(tagEntityList);

		List<PPlanDto> pplans = saveDto.getPplan();
		for (PPlanDto pplan : pplans) {
			pplanService.savePplan(pplan);
		}
		PlanTagEntity planTagEntity = new PlanTagEntity();
		planTagEntity.setPlan(PlanEntity.toPlanEntity(saveDto.getPlan()));
		for(TagEntity tagList : tagEntityList) {
			planTagEntity.setTag(tagList);
			planTagRepository.save(planTagEntity);
		}

		System.out.println(saveDto);
		return "save success";
	}

	private PlanResDto createDummyDto() {
		String[] titles = {"낭만의 도시 파리", "힐링하세요~", "이건 잘못됐어...", "신사의 나라...?"};
		Random random = new Random();

		PlanResDto dto = new PlanResDto();
		dto.setLike(random.nextInt(5, 55));
		dto.setId(random.nextInt(20000));
		dto.setTitle(titles[random.nextInt(100) % titles.length] + "_" + random.nextInt(1, 10));
		dto.setUserId("cap_user_" + random.nextInt(100));
		dto.setTags(List.of(new String[]{"계획", "낭만", "관광"}));
		return dto;
	}


}
