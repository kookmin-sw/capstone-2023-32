package com.cap.fatrip.service;

import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.entity.UserEntity;
import com.cap.fatrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;


	public static UserDto getUserFromAuth() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			return (UserDto) authentication.getPrincipal();
		} catch (Exception e) {
			log.error("로그인이 필요한 접근입니다.");
			throw e;
		}
	}

	public static boolean isLogin() {
		try {
			getUserFromAuth();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public UserEntity changeNickname(String nickname) {
		UserDto user = getUserFromAuth();
		UserEntity userEntity = userRepository.findByEmail(user.getEmail()).orElse(new UserEntity());
		userEntity.setNickname(nickname);
		return userRepository.save(userEntity);
	}

	public void save(UserDto userDto) {
		userRepository.save(UserEntity.of(userDto));
	}

	public boolean isExist(String id) {
		Optional<UserEntity> userEntityOptional = userRepository.findById(id);
		return userEntityOptional.isPresent();
	}

	public List<UserDto> findAll() {
		List<UserEntity> userEntityList = userRepository.findAll();
		List<UserDto> userDtoList = new ArrayList<>();

		for (UserEntity userEntity : userEntityList) {
			userDtoList.add(UserDto.of(userEntity));
		}
		return userDtoList;
	}

	public UserDto findById(String id) {
		Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
		return optionalUserEntity.map(UserDto::of).orElse(null);
	}

	public void update(UserDto memberDto) {
		userRepository.save(UserEntity.of(memberDto));
	}

	public void deleteById(String id) {
		userRepository.deleteById(id);
	}

}
