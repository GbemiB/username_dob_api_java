package com.recruitment.vgs.api.controller;

import com.recruitment.vgs.api.domain.Request;
import com.recruitment.vgs.api.domain.Response;
import com.recruitment.vgs.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?>  saveUser(@RequestBody Request request){
        Response response = userService.saveUser(request);
//        return response.getStatus();
     //   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Void>(response.getStatus());
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody Request request,
                                        @PathVariable("username") String username) throws Exception {
        Response response = userService.updateUser(request);
        return new ResponseEntity<Void>(response.getStatus());
    }

    @GetMapping("/{username}")
    public String getUser(@PathVariable("username") String username) throws Exception {
        String response = userService.getUser(username);
        return response;
    }

}

