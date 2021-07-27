package com.vladbudan.restapp.controller.command.impl;

import com.vladbudan.restapp.controller.command.UsersCommand;
import com.vladbudan.restapp.model.User;
import com.vladbudan.restapp.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUser implements UsersCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        UserService userService = null;

        User users = new User();
        users.setFirstName(firstName);
        users.setLastName(lastName);

        try {
            userService.save(users);
            request.setAttribute("users", users);
        }
        catch (Exception e) {
            response.sendRedirect("index.jsp");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }
}
