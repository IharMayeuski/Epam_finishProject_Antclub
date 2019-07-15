package by.epam.club.command;

import by.epam.club.controller.RequestContent;

/**
 * Interface for implements one method ececute - functional interface
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public interface ActionCommand  {
    Router execute(RequestContent content);
}
