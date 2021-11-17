package com.recruitment.vgs.api.service;

import com.recruitment.vgs.api.domain.UserRequestDto;
import com.recruitment.vgs.api.domain.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public UserResponseDto getUser(String username) {
        return null;
    }
}
