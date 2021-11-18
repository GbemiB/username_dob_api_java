package com.recruitment.vgs.api.repository;

import com.recruitment.vgs.api.domain.Request;

import java.util.Optional;

public interface IUserRepository {
    String save(Request request) throws Exception;
    String update(Request request) throws Exception;
    Optional<Request> getByUsername(String username)throws Exception;
}
