package com.vladbudan.restapp.command.entityImpl.dog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladbudan.restapp.command.Command;
import com.vladbudan.restapp.model.Dog;
import com.vladbudan.restapp.repository.impl.DogRepositoryImpl;
import com.vladbudan.restapp.repository.impl.UserRepositoryImpl;
import com.vladbudan.restapp.service.DogService;
import com.vladbudan.restapp.service.UserService;
import com.vladbudan.restapp.service.impl.DogServiceImpl;
import com.vladbudan.restapp.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.parser.ParseException;
import java.util.Optional;

public class GetDog implements Command {

    private final DogService dogService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    public GetDog() {
        userService = new UserServiceImpl(new UserRepositoryImpl());
        dogService = new DogServiceImpl(new DogRepositoryImpl());
        objectMapper = new ObjectMapper();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int userId = Integer.parseInt(pathParts[1]);
        userService.getById(userId);

        int dogId = Integer.parseInt(pathParts[3]);
        Optional<Dog> optionalDog = dogService.getDogById(dogId);

        String dogString = objectMapper.writeValueAsString(optionalDog);
        PrintWriter writer = response.getWriter();
        writer.print(dogString);

    }

}
