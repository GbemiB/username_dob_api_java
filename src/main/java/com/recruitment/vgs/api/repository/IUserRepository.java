package com.recruitment.vgs.api.repository;

import com.recruitment.vgs.api.domain.UserRequestDto;

import java.util.Optional;

public interface IUserRepository {
    String save(UserRequestDto requestDto) throws Exception;
    String update(UserRequestDto requestDto) throws Exception;
    Optional<UserRequestDto> getByUsername(String username)throws Exception;
}
