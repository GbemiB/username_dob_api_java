package com.recruitment.vgs.api.service;

import com.recruitment.vgs.api.domain.UserRequestDto;
import com.recruitment.vgs.api.domain.UserResponseDto;

public interface IUserService {
    public UserResponseDto saveUser(UserRequestDto userRequestDto);
    public UserResponseDto updateUser(UserRequestDto userRequestDto);
    public UserResponseDto getUser(String username);
}
