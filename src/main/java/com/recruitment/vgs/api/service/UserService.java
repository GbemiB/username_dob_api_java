package com.recruitment.vgs.api.service;

import com.recruitment.vgs.api.domain.UserRequestDto;
import com.recruitment.vgs.api.domain.UserResponseDto;
import com.recruitment.vgs.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = new UserResponseDto();

        String responseCode = null;
        try {
            responseCode = userRepository.save(userRequestDto);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (Objects.equals("000", responseCode)) {

                userResponseDto.setStatus(HttpStatus.NO_CONTENT);
            }
        return userResponseDto;
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = new UserResponseDto();

        String responseCode = null;
        try {
            responseCode = userRepository.update(userRequestDto);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (Objects.equals("000", responseCode)) {

                userResponseDto.setStatus(HttpStatus.NO_CONTENT);
            }
        return userResponseDto;
    }


    // use DOB and current date to calculate birthday
    @Override
    public UserResponseDto getUser(String username) throws ParseException {
        UserResponseDto userResponseDto = new UserResponseDto();
        Optional<UserRequestDto> user = null;
        try {
            user = userRepository.getByUsername(username);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (user.isPresent()) {
            UserRequestDto userRequestDto = user.get();

            String dob = userRequestDto.getDateOfBirth();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dob);

            logger.info(" Date coming from data base {} ", dob);

            Calendar currentDate = Calendar.getInstance();
            Calendar dateOfBirth = Calendar.getInstance();

            // use regex to split raw dob from db; replace - with, and pass in
            dateOfBirth.set(1996, 9, 23);
            System.out.println("currentDate :" + currentDate.getTime());
            System.out.println("dateOfBirth :" + dateOfBirth.getTime());

            // calculate how many days to birthday
            int noOfDaysToBirthDay = 0;

            if ((currentDate.get(Calendar.MONTH) == dateOfBirth.get(Calendar.MONTH)) &&
                    (currentDate.get(Calendar.DAY_OF_MONTH) == (dateOfBirth.get(Calendar.DAY_OF_MONTH)))) {
                userResponseDto.setResponseMessage("Hello, " + userRequestDto.getUsername() + "! " +
                        " Happy birthday!");
            } else {
                userResponseDto.setResponseMessage("Hello, " + userRequestDto.getUsername() + "! " +
                        " Your birthday is in " + noOfDaysToBirthDay + " day(s)");
            }

        }
        return userResponseDto;
    }
}
