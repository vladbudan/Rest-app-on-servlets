package com.vladbudan.restapp.controller.command.impl;

import com.vladbudan.restapp.controller.command.UsersCommand;
import com.vladbudan.restapp.model.User;
import com.vladbudan.restapp.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowUser implements UsersCommand {

    private static final String ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        int id;
        User user;
        UserService userService = null;

        try {
            id = Integer.parseInt(request.getParameter(ID));
            user = userService.getById(id);
            request.setAttribute("users", user);
        }
        catch (Exception e) {
            response.sendRedirect("index.jsp");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
