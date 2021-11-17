package com.recruitment.vgs.api.service;

import com.recruitment.vgs.api.domain.UserRequestDto;
import com.recruitment.vgs.api.domain.UserResponseDto;
import com.recruitment.vgs.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserRequestDto userRequestDto) throws Exception {
        try {
            String responseCode = userRepository.save(userRequestDto);
            if (Objects.equals("000", responseCode)) {
                UserResponseDto userResponseDto = new UserResponseDto();
                userResponseDto.setStatus(HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void updateUser(UserRequestDto userRequestDto) throws Exception {
        try {
            String responseCode = userRepository.update(userRequestDto);
            if (Objects.equals("000", responseCode)) {
                UserResponseDto userResponseDto = new UserResponseDto();
                userResponseDto.setStatus(HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    // use DOB and current date to calculate birthday
    @Override
    public String getUser(String username) throws Exception {
        UserResponseDto userResponseDto = new UserResponseDto();
        Optional<UserRequestDto> user = userRepository.getByUsername(username);
        if (user.isPresent()) {
            UserRequestDto userRequestDto = user.get();

           // logic to calculate birthday
            // to determine no of days to birthday
            String birthDate;
            String currentDate;
            String daysToBirthday = " ";

            if ("birthDate" != "currentDate"){
                return ("Hello," + userRequestDto.getUsername() + "! Your birthday is in " + daysToBirthday + "day(s)" );
            } else if ("birthDate" == "currentDate"){
                return ("Hello," + userRequestDto.getUsername() + " ! Happy birthday!" );
            }

        } else {
            userResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
