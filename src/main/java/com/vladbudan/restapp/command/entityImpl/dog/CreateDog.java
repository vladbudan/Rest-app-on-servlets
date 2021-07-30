package com.vladbudan.restapp.command.entityImpl.dog;


import com.vladbudan.restapp.command.Command;
import com.vladbudan.restapp.model.Dog;
import com.vladbudan.restapp.model.User;
import com.vladbudan.restapp.repository.impl.DogRepositoryImpl;
import com.vladbudan.restapp.repository.impl.UserRepositoryImpl;
import com.vladbudan.restapp.service.DogService;
import com.vladbudan.restapp.service.UserService;
import com.vladbudan.restapp.service.impl.DogServiceImpl;
import com.vladbudan.restapp.service.impl.UserServiceImpl;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.logging.Logger.getLogger;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;

public class CreateDog implements Command {

    private final DogService dogService;
    private final UserService userService;

    Logger log = getLogger(CreateDog.class.getName());

    public CreateDog() {
        userService = new UserServiceImpl(new UserRepositoryImpl());
        dogService = new DogServiceImpl(new DogRepositoryImpl());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int userId = Integer.parseInt(pathParts[1]);
        Optional<User> optionalUser = userService.getById(userId);

        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        log.info(collect);

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(collect);

        User user = optionalUser.get();

        Dog dog = new Dog();
        dog.setName((String) json.get("name"));
        dog.setColor((String) json.get("color"));
        dog.setUser(user);

        dogService.addDog(dog);

        response.setStatus(SC_CREATED);
        PrintWriter writer = response.getWriter();
        writer.print("Dog was created!");

    }

}
