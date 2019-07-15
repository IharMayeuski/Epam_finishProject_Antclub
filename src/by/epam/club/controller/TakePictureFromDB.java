package by.epam.club.controller;


import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceTakeImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.club.entity.Parameter.CONTROLLER_EXCEPTION_MESSAGE;

/**
 * The class for downloading pictures from data base to desktop
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see HttpServlet
 */
@WebServlet("/TakePictureFromDB/*")
public class TakePictureFromDB extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(TakePictureFromDB.class);

    /**
     * @param request of HttpServletRequest
     * @param response of HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String imageName = request.getPathInfo().substring(1);
        ServiceTakeImage serviceTakeImage = new ServiceTakeImage();
        try {
            byte[] content = serviceTakeImage.takeImage(imageName);
            if (content != null) {
                response.setContentType(getServletContext().getMimeType(imageName));
                response.setContentLength(content.length);
                response.getOutputStream().write(content);
            }
        } catch (ServiceException | IOException e) {
            LOGGER.error(CONTROLLER_EXCEPTION_MESSAGE, e);
        }
    }
}
