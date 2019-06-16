package by.epam.club.command.impl;

import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.controller.RequestContent;
import by.epam.club.entity.Parameter;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProvider;
import by.epam.club.service.TypeService;

import java.util.Set;

public class TakeTypeNews {
    public void typeNews(RequestContent content) {
        ServiceProvider provider = ServiceProvider.getInstance();
        String newLocale = (String) content.getSessionAttribute(Parameter.LOCAL_PARAM);
        TypeService typeService = provider.getTypeService();
        try {
            Set<TypeNews> typeNews = typeService.takeAllUndeleted();
            content.putSessionAttribute(Parameter.TYPES_PARAM, typeNews);

        } catch (ServiceException e) {
           e.printStackTrace();
            content.putRequestAttribute(Parameter.ERROR_PARAM, MessageManager.getProperty(Parameter.SERVICE_EXCEPTION_MESSAGE, newLocale));
        }
    }
}
