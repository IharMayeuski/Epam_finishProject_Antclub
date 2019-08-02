package by.epam.club.controller;

import by.epam.club.exception.ServiceException;
import by.epam.club.service.article.ArticleService;
import by.epam.club.service.article.ArticleServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.club.entity.Parameter.*;
/**
 * This servlet is created for upping and downing likes for articles
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see HttpServlet
 */
@WebServlet("/someservlet/*")
public class AjaxController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(AjaxController.class);

    /**
     * @param request method doGet receives this parameter HttpServletRequest
     * @param response method doGet receives this parameter HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String jsonResponse;
        String newsId = request.getParameter(ID_NEWS_PARAM);
        String positiveRating = request.getParameter(POSITIVE_RATING_PARAM);
        ArticleService articleService = new ArticleServiceImpl();
        String negativeRating = request.getParameter(NEGATIVE_RATING_PARAM);
        String type = request.getParameter(TYPE_PARAM);
        String userId = request.getParameter(ID_USER_PARAM);
        if (!userId.isEmpty()) {
            try {
                if (type.equals(LIKE_PARAM)) {
                    articleService.uppPositiveRating(userId, newsId, positiveRating);
                } else if (type.equals(DISLIKE_PARAM)) {
                    articleService.uppNegativeRating(userId, newsId, negativeRating);
                }
                jsonResponse = SUCCESS_PARAM;
                sendResponse(response, jsonResponse);
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage());
                jsonResponse = NO_PARAM;
                sendResponse(response, jsonResponse);
            }
        } else {
            jsonResponse = "you cant vote";
            sendResponse(response, jsonResponse);
        }
    }

    /**
     * @param response for sending ajax
     * @param forJson this String for sending json to .jsp
     */
    private void sendResponse(HttpServletResponse response, String forJson) {
        try {
            response.setContentType(APPLICATION_JSON);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String jsonResponse = gson.toJson(forJson);
            response.getWriter().write(jsonResponse);
        } catch (IOException e1) {
            LOGGER.warn(UNKNOWN_MISTAKE_MESSAGE);
        }
    }
}
