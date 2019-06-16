package by.epam.club.command.impl;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.redirect.AuthorizationCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.Article;
import by.epam.club.entity.Parameter;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ArticleService;
import by.epam.club.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class FindArticleCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationCommand.class);

    @Override
    public Router execute(RequestContent content){
        String page = ConfigurationManager.getProperty(Parameter.LOOK_TYPE_PAGE);
        String locale = (String) content.getSessionAttribute(Parameter.LOCAL_PARAM);
        Set<Article> articles = null;
        TransmisionType transmitionType = TransmisionType.FORWARD;
        int typeNews = Integer.parseInt(content.getRequestParameters(Parameter.LINK_ID_PARAM , 0));
        ServiceProvider provider = ServiceProvider.getInstance();

        ArticleService service = provider.getArticleService();
        try {
            articles = service.takeAllUnbannedUndeletedArticles(typeNews);
        } catch (ServiceException e) {
            LOGGER.info(Parameter.SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(Parameter.ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
            page = ConfigurationManager.getProperty(Parameter.DEFAULT_PAGE);
        }

        content.putRequestAttribute(Parameter.ARTICLES_PARAM, articles);
        System.out.println(articles.size()+" size collection");
        return new Router(page, transmitionType);
    }
}
