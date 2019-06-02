package by.epam.club.command;

import by.epam.club.controller.RequestContent;
import by.epam.club.controller.Router;
import by.epam.club.exception.AntCommandException;

public interface ActionCommand {
    Router execute(RequestContent content) throws AntCommandException;
}
