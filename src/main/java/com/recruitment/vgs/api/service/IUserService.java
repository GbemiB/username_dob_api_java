package com.recruitment.vgs.api.service;

import com.recruitment.vgs.api.domain.Request;
import com.recruitment.vgs.api.domain.Response;

import java.text.ParseException;

public interface IUserService {
     Response saveUser(Request request);
     Response updateUser(Request request);
     String getUser(String username) throws ParseException;
}
