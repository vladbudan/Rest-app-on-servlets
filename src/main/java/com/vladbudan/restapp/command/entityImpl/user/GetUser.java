package com.vladbudan.restapp.command.entityImpl.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladbudan.restapp.command.Command;
import com.vladbudan.restapp.model.User;
import com.vladbudan.restapp.repository.impl.UserRepositoryImpl;
import com.vladbudan.restapp.service.UserService;
import com.vladbudan.restapp.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class GetUser implements Command {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    public GetUser() {
        userService = new UserServiceImpl(new UserRepositoryImpl());
        objectMapper = new ObjectMapper();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int userId = Integer.parseInt(pathParts[1]);
        Optional<User> optionalUser = userService.getById(userId);

        String userString = objectMapper.writeValueAsString(optionalUser);
        PrintWriter writer = response.getWriter();
        writer.print(userString);

    }

}
