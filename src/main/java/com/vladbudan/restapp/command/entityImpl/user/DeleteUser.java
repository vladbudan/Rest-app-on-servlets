package com.vladbudan.restapp.command.entityImpl.user;

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

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class DeleteUser implements Command {

    private final UserService userService;

    public DeleteUser() {
        userService = new UserServiceImpl(new UserRepositoryImpl());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int userId = Integer.parseInt(pathParts[1]);
        userService.getById(userId);

        userService.delete(userId);
        PrintWriter writer = response.getWriter();
        writer.println("User was deleted successfully!");

    }

}
