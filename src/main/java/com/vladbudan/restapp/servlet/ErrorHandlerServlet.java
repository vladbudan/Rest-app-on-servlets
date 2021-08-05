package com.vladbudan.restapp.servlet;

import com.vladbudan.restapp.exception.HibernateConfigException;
import com.vladbudan.restapp.exception.RepositoryException;
import com.vladbudan.restapp.exception.ResourceNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;
import static javax.servlet.RequestDispatcher.ERROR_EXCEPTION;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet(urlPatterns = "/errorHandler")
public class ErrorHandlerServlet extends HttpServlet {

    Logger log = getLogger(ErrorHandlerServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Exception exception = (Exception) req.getAttribute(ERROR_EXCEPTION);
        PrintWriter writer = resp.getWriter();

        if (exception instanceof ResourceNotFoundException) {
            resp.setStatus(SC_NOT_FOUND);
            writer.println(exception.getMessage());
            log.info("Entity isn't exist!");

        }
        if (exception instanceof RepositoryException) {
            resp.setStatus(SC_NOT_FOUND);
            writer.println(exception.getMessage());
            log.info("Entity isn't exist!");

        }
        if (exception instanceof IOException) {
            writer.println(exception.getMessage());
            log.info(exception.getMessage());

        }
        if (exception instanceof ServletException) {
            writer.println(exception.getMessage());
            log.info(exception.getMessage());

        }
        if (exception instanceof ParseException) {
            writer.println(exception.getMessage());
            log.info(exception.getMessage());

        }
        if (exception instanceof HibernateConfigException) {
            writer.println(exception.getMessage());
            log.info(exception.getMessage());
        }

    }

}
