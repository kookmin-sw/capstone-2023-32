package com.cap.fatrip.service;

import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.entity.UserEntity;
import com.cap.fatrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

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
