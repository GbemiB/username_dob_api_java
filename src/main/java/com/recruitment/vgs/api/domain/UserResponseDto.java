package com.recruitment.vgs.api.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserResponseDto {
    private HttpStatus responseCode;
    private String responseMessage;

}
