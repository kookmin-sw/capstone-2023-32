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
    //1.dto->entity 변환
    //2.repository의 save 메소드 호출
    public void save(UserDto userDto){
        userRepository.save(UserEntity.toUserEntity(userDto));
    }

    public UserDto login(UserDto userDto) {
        Optional<UserEntity> user = userRepository.findById(userDto.getId());
        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            if (userEntity.getPassword().equals(userDto.getPassword())) {
                return UserDto.of(userEntity);
            }
        }
        return null;
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
        userRepository.save(UserEntity.toUserEntity(memberDto));
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public void saveAndFindUser(UserDto userDto) {

    }
}
