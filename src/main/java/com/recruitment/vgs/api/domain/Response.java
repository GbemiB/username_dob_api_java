package com.recruitment.vgs.api.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {
    private HttpStatus status;
    private String responseMessage;
}
