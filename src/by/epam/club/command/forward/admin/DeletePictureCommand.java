package by.epam.club.command.forward.admin;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.TransmisionType;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.picture.PictureService;
import by.epam.club.service.picture.PictureServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.club.entity.Parameter.*;
import static by.epam.club.entity.Parameter.ERROR_PARAM;

/**
 * Command for mark 'Picture is deleted'
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */


public class DeletePictureCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteCommentCommand.class);

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        String locale = (String) content.getSessionAttribute(LOCAL_PARAM);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        String pictureId = content.getRequestParameters(PICTURE_PARAM, 0);
        PictureService pictureService = new PictureServiceImpl();

        try {
            pictureService.deletePicture(pictureId);
            content.putRequestAttribute(UPDATE_ALL_IS_OK_PARAM, MessageManager.getProperty(UPDATE_ALL_IS_OK_PARAM, locale));

        } catch (ServiceException e) {
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
        }
        return new Router(page, transmitionType);
    }
}