package com.vladbudan.restapp.command.entityImpl.cat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladbudan.restapp.command.Command;
import com.vladbudan.restapp.exception.ResourceNotFoundException;
import com.vladbudan.restapp.model.Cat;
import com.vladbudan.restapp.model.User;
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

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class DeleteCat implements Command {

    private final CatService catService;
    private final UserService userService;

    public DeleteCat() {
        userService = new UserServiceImpl(new UserRepositoryImpl());
        catService = new CatServiceImpl(new CatRepositoryImpl());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int userId = Integer.parseInt(pathParts[1]);
        userService.getById(userId);

        int catId = Integer.parseInt(pathParts[3]);
        catService.getCatById(catId);

        catService.deleteCat(catId);
        PrintWriter writer = response.getWriter();
        writer.println("Cat was deleted successfully!");

    }

}
