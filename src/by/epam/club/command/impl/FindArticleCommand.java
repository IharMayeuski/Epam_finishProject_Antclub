package by.epam.club.command.impl;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.Article;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ArticleService;
import by.epam.club.service.ServiceProvider;

import java.util.Set;

public class FindArticleCommand implements ActionCommand {
    private static final String ARTICLE = "path.page.admin.look.type";

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(ARTICLE);
        TransmisionType transmitionType = TransmisionType.FORVARD;
        int typeNews = Integer.parseInt(content.getRequestParameters("link_id", 0));
        //    String newLocale = (String) content.getSessionAttribute("local");

        ServiceProvider provider = ServiceProvider.getInstance();
        ArticleService service = provider.getArticleService();
        Set<Article> articles = null;
        try {
            articles = service.takeAllUnbannedUndeletedArticles(typeNews);
        } catch (ServiceException e) {
            e.printStackTrace();//todo логи
        }
        content.putRequestAttribute("articles", articles);
        return new Router(page, transmitionType);
    }
}
