package by.epam.club.controller.command;

import by.epam.club.exception.ControllerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Commander {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException;
}

