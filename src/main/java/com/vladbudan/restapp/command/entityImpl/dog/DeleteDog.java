package com.vladbudan.restapp.command.entityImpl.dog;

import com.vladbudan.restapp.command.Command;
import com.vladbudan.restapp.repository.impl.DogRepositoryImpl;
import com.vladbudan.restapp.repository.impl.UserRepositoryImpl;
import com.vladbudan.restapp.service.DogService;
import com.vladbudan.restapp.service.UserService;
import com.vladbudan.restapp.service.impl.DogServiceImpl;
import com.vladbudan.restapp.service.impl.UserServiceImpl;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteDog implements Command {

    private final DogService dogService;
    private final UserService userService;

    public DeleteDog() {
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
        dogService.getDogById(dogId);

        dogService.deleteDog(dogId);
        PrintWriter writer = response.getWriter();
        writer.println("Dog was deleted successfully!");

    }

}
