package by.epam.club.command;

import by.epam.club.controller.RequestContent;

public interface ActionCommand  {
    Router execute(RequestContent content);
}
