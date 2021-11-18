package com.recruitment.vgs.api.controller;

import com.recruitment.vgs.api.domain.Request;
import com.recruitment.vgs.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping("/check/{username}")
    public Request testFindByusername(@PathVariable("username") String username) throws Exception {
        if (userRepository.getByUsername(username).isPresent())
            return userRepository.getByUsername(username).get();
        return null;
    }


    @PutMapping("/check/{username}")
    public String testUpdateUser(@RequestBody Request request,
                               @PathVariable("username") String username) throws Exception {
      return userRepository.update(request);
    }

    @PostMapping("/check/add")
    public String testSaveUser(@RequestBody Request request) throws Exception {
        return userRepository.save(request);
    }

}
