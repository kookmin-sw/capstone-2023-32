package com.cap.fatrip.service;

import com.cap.fatrip.dto.inbound.PlanDetailSaveDto;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
@AutoConfigureMockMvc
public class savePlanTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSavePlan() throws Exception {
        // JSON 데이터 생성
        String json = new String(Files.readAllBytes(Paths.get("C:\\Users\\정해준\\OneDrive\\바탕 화면\\backend\\fastrip-server\\etc\\plansInput.json")));
        // JSON 데이터를 Java 객체로 변환
        PlanDetailSaveDto planDetailSaveDto = objectMapper.readValue(json, PlanDetailSaveDto.class);

        // API 호출
        mockMvc.perform(MockMvcRequestBuilders.post("/api/plan/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
