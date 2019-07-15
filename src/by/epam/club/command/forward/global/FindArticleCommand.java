package by.epam.club.command.forward.global;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.Article;
import by.epam.club.entity.Parameter;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.article.ArticleService;
import by.epam.club.service.article.ArticleServiceImpl;
import by.epam.club.service.type.TypeService;
import by.epam.club.service.type.TypeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.epam.club.entity.Parameter.*;

/**
 * Command for transition user on the page with articles and commands
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class FindArticleCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationCommand.class);

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content){
        String page = ConfigurationManager.getProperty(LOOK_ARTICLE_PAGE_FORVARD);
        String locale = (String) content.getSessionAttribute(LOCAL_PARAM);
        List<Article> articles;
        TransmisionType transmitionType = TransmisionType.FORWARD;
        int typeNews = Integer.parseInt(content.getRequestParameters(LINK_ID_PARAM , 0));
        ArticleService service= new ArticleServiceImpl();
        TypeService typeService = new TypeServiceImpl();
        try {
            articles = service.takeAllUnbannedUndeletedArticles(typeNews);
            TypeNews type;
            type = typeService.takeOne(typeNews);
            content.putRequestAttribute(ARTICLES_PARAM, articles);
            content.putRequestAttribute(TYPES_PARAM, type);
        } catch (ServiceException e) {
            LOGGER.info(Parameter.SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
            page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        }
        return new Router(page, transmitionType);
    }
}
