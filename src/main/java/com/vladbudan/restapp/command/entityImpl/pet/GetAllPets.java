package com.vladbudan.restapp.command.entityImpl.pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladbudan.restapp.command.Command;
import com.vladbudan.restapp.model.Cat;
import com.vladbudan.restapp.model.Dog;
import com.vladbudan.restapp.repository.impl.CatRepositoryImpl;
import com.vladbudan.restapp.repository.impl.DogRepositoryImpl;
import com.vladbudan.restapp.repository.impl.UserRepositoryImpl;
import com.vladbudan.restapp.service.CatService;
import com.vladbudan.restapp.service.DogService;
import com.vladbudan.restapp.service.UserService;
import com.vladbudan.restapp.service.impl.CatServiceImpl;
import com.vladbudan.restapp.service.impl.DogServiceImpl;
import com.vladbudan.restapp.service.impl.UserServiceImpl;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetAllPets implements Command {

    private final UserService userService;
    private final CatService catService;
    private final DogService dogService;
    private final ObjectMapper objectMapper;

    public GetAllPets() {
        objectMapper = new ObjectMapper();
        userService = new UserServiceImpl(new UserRepositoryImpl());
        catService = new CatServiceImpl(new CatRepositoryImpl());
        dogService = new DogServiceImpl(new DogRepositoryImpl());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int userId = Integer.parseInt(pathParts[1]);
        userService.getById(userId);

        List<Cat> cats = catService.getAllCats();
        List<Dog> dogs = dogService.getAllDogs();

        String dogsString = objectMapper.writeValueAsString(dogs);
        String catsString = objectMapper.writeValueAsString(cats);
        PrintWriter writer = response.getWriter();
        writer.print(dogsString);
        writer.print(catsString);

    }

}
