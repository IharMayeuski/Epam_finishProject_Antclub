package by.epam.club.command;

import by.epam.club.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Commander {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException;
}

