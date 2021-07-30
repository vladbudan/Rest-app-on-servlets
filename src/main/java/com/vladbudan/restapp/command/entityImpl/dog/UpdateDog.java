package com.vladbudan.restapp.command.entityImpl.dog;

import com.vladbudan.restapp.command.Command;
import com.vladbudan.restapp.model.Dog;
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

public class UpdateDog implements Command {

    private final DogService dogService;
    private final UserService userService;

    Logger log = getLogger(UpdateDog.class.getName());

    public UpdateDog() {
        userService = new UserServiceImpl(new UserRepositoryImpl());
        dogService = new DogServiceImpl(new DogRepositoryImpl());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int userId = Integer.parseInt(pathParts[1]);
        userService.getById(userId);

        int dogId = Integer.parseInt(pathParts[3]);
        Optional<Dog> optionalDog = dogService.getDogById(dogId);

        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        log.info(collect);

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(collect);

        Dog dog = optionalDog.get();

        dog.setName((String) json.get("name"));
        dog.setColor((String) json.get("color"));

        dogService.update(dog);

        PrintWriter writer = response.getWriter();
        writer.println("Dog was updated successfully!");

    }

}
