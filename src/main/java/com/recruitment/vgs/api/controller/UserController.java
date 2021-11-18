package com.recruitment.vgs.api.controller;

import com.recruitment.vgs.api.domain.UserRequestDto;
import com.recruitment.vgs.api.domain.UserResponseDto;
import com.recruitment.vgs.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto saveUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto response = userService.saveUser(userRequestDto);
        return response;
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDto userRequestDto,
                                        @PathVariable("username") String username) throws Exception {
        UserResponseDto response = userService.updateUser(userRequestDto);
        return new ResponseEntity<Void>(response.getStatus());
    }

    @GetMapping("/{username}")
    public UserResponseDto getUser(@PathVariable("username") String username) throws Exception {
        UserResponseDto response = userService.getUser(username);
        return response;
    }

}

