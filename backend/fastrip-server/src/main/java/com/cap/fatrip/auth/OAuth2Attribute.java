package com.cap.fatrip.auth;

import com.cap.fatrip.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OAuth2Attribute {
    private Map<String, Object> attributes;
    private String attributeKey;
    private String name;
    private String nickname;
    private String email;
    private UserEntity.Role role;
    private String picture;

    public static OAuth2Attribute of(String registrationId,
                                     String attributeKey,
                                     Map<String, Object> attributes) {
        /* 구글인지 네이버인지 카카오인지 구분하기 위한 메소드 (ofNaver, ofKaKao) */

        switch (registrationId) {
            case "google":
                return ofGoogle(attributeKey, attributes);
            case "naver":
                return ofNaver("id", attributes);
            case "kakao":
                return ofKakao("email", attributes);
        }
        throw new RuntimeException();
    }

    private static OAuth2Attribute ofGoogle(String attributeKey,
                                            Map<String, Object> attributes) {
        return OAuth2Attribute.builder()
                .name((String) attributes.get("given_name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .nickname((String) attributes.get("name"))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    private static OAuth2Attribute ofKakao(String attributeKey,
                                           Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuth2Attribute.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .picture((String)kakaoProfile.get("profile_image_url"))
                .nickname((String) attributes.get("nickname"))
                .attributes(kakaoAccount)
                .attributeKey(attributeKey)
                .build();
    }

    private static OAuth2Attribute ofNaver(String attributeKey,
                                           Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuth2Attribute.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .nickname((String) attributes.get("nickname"))
                .attributes(response)
                .attributeKey(attributeKey)
                .build();
    }

    public Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", attributeKey);
        map.put("key", attributeKey);
        map.put("name", name);
        map.put("email", email);
        map.put("picture", picture);

        return map;
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(RandomStringUtils.random(10, true, true))
//                .password(RandomStringUtils.random(10, true, true))
                .name(name)
                .email(email)
                .nickname(nickname)
                .role(UserEntity.Role.USER)
                .build();
    }
}