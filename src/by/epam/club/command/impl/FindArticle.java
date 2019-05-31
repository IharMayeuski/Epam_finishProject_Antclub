package by.epam.club.command.impl;

import by.epam.club.command.ActionCommand;
import by.epam.club.manager.ConfigurationManager;
import by.epam.club.entity.Article;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ArticleService;
import by.epam.club.service.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class FindArticle implements ActionCommand {
    private static final String ARTICLE = "path.page.admin.look.type";

    @Override
    public String execute(HttpServletRequest request) {//todo нужно подумать надо методом fiixme
        HttpSession session = request.getSession();

        String cc = request.getQueryString().replaceAll("\\D+","");
        int typeNews = Integer.parseInt(cc);

        String newLocale = (String) session.getAttribute("local");

        ServiceProvider provider = ServiceProvider.getInstance();
        ArticleService service = provider.getArticleService();

        Set<Article> articles = null;
        try {
            articles = service.takeAllUnbannedUndeletedArticles(typeNews);
        } catch (ServiceException e) {
            e.printStackTrace();//todo логи
        }
        request.setAttribute("articles", articles);



        return ConfigurationManager.getProperty(ARTICLE);
    }
}
