package by.epam.club.service;

import by.epam.club.service.impl.ArticleServiceImpl;
import by.epam.club.service.impl.TypeServiceImpl;
import by.epam.club.service.impl.UserServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {}

    private UserService service = new UserServiceImpl();
    private ArticleService articleService = new ArticleServiceImpl();
    private TypeService typeService = new TypeServiceImpl();

    public UserService getUserService() {
        return service;
    }
    public TypeService getTypeService (){return typeService;}
    public ArticleService getArticleService() {
        return articleService;
    }

    public static ServiceProvider getInstance() {  return instance;
    }
}