package com.vladbudan.restapp.servlet;

import com.vladbudan.restapp.command.Command;
import com.vladbudan.restapp.command.CommandProvider;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static com.vladbudan.restapp.constants.Constants.DELETE;
import static com.vladbudan.restapp.constants.Constants.GET;
import static com.vladbudan.restapp.constants.Constants.POST;
import static com.vladbudan.restapp.constants.Constants.PUT;
import static java.util.logging.Logger.getLogger;


@WebServlet(name = "MainController", urlPatterns = "/users/*")
public class MainServlet extends HttpServlet {

    Logger log = getLogger(MainServlet.class.getName());

    public MainServlet() {
        CommandProvider commandProvider = new CommandProvider();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            String commandStr = GET + process(request.getRequestURI());

            Command command = CommandProvider.getCommand(commandStr);
            command.execute(request, response);
        }
        catch (IOException | ParseException | ServletException e) {
            log.info(e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            String commandStr = POST + process(request.getRequestURI());

            Command command = CommandProvider.getCommand(commandStr);
            command.execute(request, response);

        } catch (IOException | ParseException | ServletException e) {
            log.info(e.getMessage());
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {

        try {
            String commandStr = PUT + process(request.getRequestURI());

            Command command = CommandProvider.getCommand(commandStr);
            command.execute(request, response);

        } catch (IOException | ParseException | ServletException e) {
            log.info(e.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

        try {
            String commandStr = DELETE + process(request.getRequestURI());

            Command command = CommandProvider.getCommand(commandStr);
            command.execute(request, response);

        } catch (IOException | ParseException | ServletException e) {
            log.info(e.getMessage());
        }

    }

    private String process(String uri) {

        String commandName = uri.substring(1);
        return commandName.replaceAll("/[\\d]+", "/id");

    }

}