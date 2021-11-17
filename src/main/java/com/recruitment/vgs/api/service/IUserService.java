package com.recruitment.vgs.api.service;

import com.recruitment.vgs.api.domain.UserRequestDto;

public interface IUserService {
     void saveUser(UserRequestDto userRequestDto) throws Exception;
     void updateUser(UserRequestDto userRequestDto) throws Exception;
     String getUser(String username) throws Exception;
}
