package com.recruitment.vgs.api.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Request {
    private String username;
    private Date dateOfBirth;
}
