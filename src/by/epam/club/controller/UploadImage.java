package by.epam.club.controller;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.article.ArticleService;
import by.epam.club.service.article.ArticleServiceImpl;
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
/**
 * This special servlet is created for upload pictured to data base
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see HttpServlet
 */
@WebServlet(name = "/UploadImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 1024 * 1024)
public class UploadImage extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(UploadImage.class);

    /**
     * @param request take HttpServletRequest
     * @param response for sending by post method
     * @throws ServletException this method cant throw IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String type = request.getParameter(TYPE_PARAM);
        String article = request.getParameter(ARTICLE_PARAM);
        String userId = request.getParameter(USER_ID_PARAM);
        String text = request.getParameter(TEXT_PARAM);
        String locale = (String) request.getSession().getAttribute(LOCAL_PARAM);
        RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD));
        try {
            Part part = request.getPart(IMAGE_PARAM);
            if (type != null && type.equals(NEW_PARAM)) {
                TypeService typeService = new TypeServiceImpl();
                typeService.createNewType(text, part); // FIXME: 7/1/2019
                dispatcher = request.getRequestDispatcher(ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD));
            } else if (article != null && article.equals(NEW_PARAM)) {
                String title = request.getParameter(TITLE_PARAM);
                String fileName = request.getParameter(FILE_NAME_PARAM);
                ArticleService articleService = new ArticleServiceImpl();
                User user = (User) request.getSession().getAttribute(USER_PARAM);
                String typesId = Integer.toString((int) request.getSession().getAttribute(TYPE_ID));
                articleService.createNew(fileName, title, text, user.getId(), Integer.parseInt(typesId), part);
            } else if (article != null && article.equals(NEW_PHOTO_PARAM)) {
                String articleId = (String) request.getSession().getAttribute(ARTICLE_ID_PARAM);
                ArticleService articleService = new ArticleServiceImpl();
                articleService.createNewPicture(articleId, text, part);
            } else if (userId != null) {
                UserService uploadImage = new UserServiceImpl();
                uploadImage.createUserPic(userId, part);
            }
            request.setAttribute(UPDATE_ALL_IS_OK_PARAM, MessageManager.getProperty(UPDATE_ALL_IS_OK_PARAM, locale));
            // FIXME: 6/25/2019 по причине редиректа - сообщения не доходят
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            request.setAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
            if (type != null && type.equals(NEW_PARAM)) {
                dispatcher = request.getRequestDispatcher(ConfigurationManager.getProperty(NEW_TYPE_PAGE_FORVARD));
            } else if (article != null && article.equals(NEW_PARAM)) {
                dispatcher = request.getRequestDispatcher(ConfigurationManager.getProperty(NEW_ARTICLE_PAGE_FORVARD));
            } else if (article != null && article.equals(NEW_PHOTO_PARAM)) {
                dispatcher = request.getRequestDispatcher(ConfigurationManager.getProperty(NEW_ARTICLE_PICTURE_PAGE_FORVARD));
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage(), e);
        } finally {
            try {
                dispatcher.forward(request, response);
            } catch (IOException e) {
                LOGGER.info(UNKNOWN_MISTAKE_MESSAGE);
                request.setAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
            }
        }
    }
}

