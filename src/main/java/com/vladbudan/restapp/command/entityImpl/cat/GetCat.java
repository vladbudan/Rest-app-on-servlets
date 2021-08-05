package com.vladbudan.restapp.command.entityImpl.cat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladbudan.restapp.command.Command;
import com.vladbudan.restapp.model.Cat;
import com.vladbudan.restapp.repository.impl.CatRepositoryImpl;
import com.vladbudan.restapp.repository.impl.UserRepositoryImpl;
import com.vladbudan.restapp.service.CatService;
import com.vladbudan.restapp.service.UserService;
import com.vladbudan.restapp.service.impl.CatServiceImpl;
import com.vladbudan.restapp.service.impl.UserServiceImpl;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class GetCat implements Command {

    private final CatService catService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    public GetCat() {
        userService = new UserServiceImpl(new UserRepositoryImpl());
        catService = new CatServiceImpl(new CatRepositoryImpl());
        objectMapper = new ObjectMapper();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int userId = Integer.parseInt(pathParts[1]);

        userService.getById(userId);

        int catId = Integer.parseInt(pathParts[3]);
        Optional<Cat> optionalCat = catService.getCatById(catId);

        String catString = objectMapper.writeValueAsString(optionalCat);
        PrintWriter writer = response.getWriter();
        writer.print(catString);

    }

}
