package com.recruitment.vgs.api.service;

import com.recruitment.vgs.api.domain.UserRequestDto;
import com.recruitment.vgs.api.domain.UserResponseDto;

import java.text.ParseException;

public interface IUserService {
     UserResponseDto saveUser(UserRequestDto userRequestDto);
     UserResponseDto updateUser(UserRequestDto userRequestDto);
     UserResponseDto getUser(String username) throws ParseException;
}
