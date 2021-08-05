package com.vladbudan.restapp.command.entityImpl.user;

import com.vladbudan.restapp.command.Command;
import com.vladbudan.restapp.model.User;
import com.vladbudan.restapp.repository.impl.UserRepositoryImpl;
import com.vladbudan.restapp.service.UserService;
import com.vladbudan.restapp.service.impl.UserServiceImpl;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.logging.Logger.getLogger;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;

public class CreateUser implements Command {

    private final UserService userService;

    Logger log = getLogger(CreateUser.class.getName());

    public CreateUser() {
        userService = new UserServiceImpl(new UserRepositoryImpl());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {

        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        log.info(collect);

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(collect);

        User user = new User();
        user.setFirstName((String) json.get("firstName"));
        user.setLastName((String) json.get("lastName"));

        userService.save(user);

        response.setStatus(SC_CREATED);
        PrintWriter writer = response.getWriter();
        writer.println("User was created!");

    }

}
