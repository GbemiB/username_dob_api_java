package com.recruitment.vgs.api.service;

import com.recruitment.vgs.api.domain.Request;
import com.recruitment.vgs.api.domain.Response;
import com.recruitment.vgs.api.repository.UserRepository;
import com.recruitment.vgs.api.util.DateDiffCalculator;
import com.recruitment.vgs.api.util.DateToCalender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

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
    public String getUser(String username) throws Exception {
        Response response = new Response();
        DateToCalender obj = new DateToCalender();
        DateDiffCalculator dateDiffCalculator = new DateDiffCalculator();

        Optional<Request> user = null;
        try {
            user = userRepository.getByUsername(username);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (user.isPresent()) {
            Request request = user.get();

            Date dob = request.getDateOfBirth();
            Date date2 = new Date();

            Calendar dateOfBirth = obj.dateToCalendar(dob);
            Calendar currentDate = Calendar.getInstance();

            Long noOfDaysToBirthDay = DateDiffCalculator.calculateDays(date2, dob);
            log.info(" Numbers of Days to birthday  {} ", noOfDaysToBirthDay);

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
