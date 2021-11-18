package com.recruitment.vgs.api.controller;

import com.recruitment.vgs.api.domain.Request;
import com.recruitment.vgs.api.domain.Response;
import com.recruitment.vgs.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody Request userRequestDto){
        Response response = userService.saveUser(userRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody Request userRequestDto,
                                        @PathVariable("username") String username) throws Exception {
        Response response = userService.updateUser(userRequestDto);
        return new ResponseEntity<Void>(response.getStatus());
    }

    @GetMapping("/{username}")
    public String getUser(@PathVariable("username") String username) throws Exception {
        String response = userService.getUser(username);
        return response;
    }

}

