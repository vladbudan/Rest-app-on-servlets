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
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.logging.Logger.getLogger;

public class UpdateUser implements Command {

    private final UserService userService;

    Logger log = getLogger(UpdateUser.class.getName());

    public UpdateUser() {
        userService = new UserServiceImpl(new UserRepositoryImpl());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int userId = Integer.parseInt(pathParts[1]);
        Optional<User> optionalUser = userService.getById(userId);

        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        log.info(collect);

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(collect);

        User user = optionalUser.get();

        user.setFirstName((String) json.get("firstName"));
        user.setLastName((String) json.get("lastName"));

        userService.update(user);

        PrintWriter writer = response.getWriter();
        writer.println("User was updated successfully!");

    }

}
