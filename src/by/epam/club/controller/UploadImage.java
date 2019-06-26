package by.epam.club.controller;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.type.TypeService;
import by.epam.club.service.type.TypeServiceImpl;
import by.epam.club.service.user.UserService;
import by.epam.club.service.user.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

import static by.epam.club.entity.Parameter.*;

@WebServlet(name = "/UploadImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 1024 * 1024)
public class UploadImage extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(UploadImage.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String type = request.getParameter("type");
        String userId = request.getParameter(USER_ID_PARAM);
        String newLocale = (String) request.getSession().getAttribute(LOCAL_PARAM);
        RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationManager.getProperty(INDEX_PAGE));
        try {
            Part part = request.getPart(IMAGE_PARAM);
            if (type != null && type.equals("new")) {
                String text = request.getParameter("text");
                TypeService typeService = new TypeServiceImpl();
                typeService.createNewType(text, part);
                request.setAttribute(UPDATE_ALL_IS_OK_PARAM, UPDATE_ALL_IS_OK_PARAM); // FIXME: 6/25/2019 по причине редиректа - сообщения не доходят
            } else if (userId != null) {
                UserService uploadImage = new UserServiceImpl();
                uploadImage.createUserPic(userId, part);
            }
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            request.setAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), newLocale));
            if (type != null && type.equals("new")) {
                dispatcher = request.getRequestDispatcher(ConfigurationManager.getProperty(NEW_TYPE_PAGE_FORVARD));
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage(), e);
        } finally {
            try {
                dispatcher.forward(request, response);
            } catch (IOException e) {
                LOGGER.info(UNKNOWN_MISTAKE_MESSAGE);
                request.setAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), newLocale));
            }
        }
    }
}

