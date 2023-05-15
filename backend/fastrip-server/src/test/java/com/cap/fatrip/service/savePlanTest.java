package com.cap.fatrip.service;

import com.cap.fatrip.dto.inbound.savePlanDto;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

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
        String json = "{\"plan\":{\"p_id\":\"111\"},\"pplan\":[{\"plan\":{\"id\":1},\"p_time\":\"2000\",\"p_comment\":\"hello\",\"p_seq\":\"1\"},{\"plan\":{\"id\":1},\"p_time\":\"20002\",\"p_comment\":\"hello2\",\"p_seq\":\"2\"}]}";

        // JSON 데이터를 Java 객체로 변환
        savePlanDto saveDto = objectMapper.readValue(json, savePlanDto.class);

        // API 호출
        mockMvc.perform(MockMvcRequestBuilders.post("/api/plan/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
