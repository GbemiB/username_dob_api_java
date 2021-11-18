package com.recruitment.vgs.api.service;

import com.recruitment.vgs.api.domain.Request;
import com.recruitment.vgs.api.domain.Response;
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
    public Response saveUser(Request request) {
        Response response = new Response();

        String responseCode = null;
        try {
            responseCode = userRepository.save(request);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (Objects.equals("000", responseCode)) {
                response.setStatus(HttpStatus.NO_CONTENT);
            }
        return response;
    }

    @Override
    public Response updateUser(Request request) {
        Response response = new Response();
        String responseCode = null;
        try {
            responseCode = userRepository.update(request);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (Objects.equals("000", responseCode)) {

                response.setStatus(HttpStatus.NO_CONTENT);
            }
        return response;
    }

    @Override
    public String getUser(String username) throws ParseException {
        Response response = new Response();
        Optional<Request> user = null;
        try {
            user = userRepository.getByUsername(username);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (user.isPresent()) {
            Request request = user.get();

            String dob = request.getDateOfBirth();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dob);

            logger.info(" Date coming from data base {} ", dob);

            Calendar currentDate = Calendar.getInstance();
            Calendar dateOfBirth = Calendar.getInstance();

            int noOfDaysToBirthDay = 0;

            if ((currentDate.get(Calendar.MONTH) == dateOfBirth.get(Calendar.MONTH)) &&
                    (currentDate.get(Calendar.DAY_OF_MONTH) == (dateOfBirth.get(Calendar.DAY_OF_MONTH)))) {
                response.setResponseMessage("Hello, " + request.getUsername() + "! " +
                        " Happy birthday!");
            } else {
                response.setResponseMessage("Hello, " + request.getUsername() + "! " +
                        " Your birthday is in " + noOfDaysToBirthDay + " day(s)");
            }

        }
        return response.getResponseMessage();
    }
}
