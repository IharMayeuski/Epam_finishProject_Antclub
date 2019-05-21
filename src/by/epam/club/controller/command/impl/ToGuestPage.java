package by.epam.club.controller.command.impl;

import by.epam.club.controller.command.Commander;
import by.epam.club.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToGuestPage implements Commander {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

    }
}
