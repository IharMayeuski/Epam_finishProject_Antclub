package by.epam.club.service.type;

import by.epam.club.entity.TypeNews;
import by.epam.club.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

public interface TypeService {
    List<TypeNews> takeAllUndeleted() throws ServiceException;

    List<TypeNews> takeAll() throws ServiceException;

    void createNewType(String text, Part part) throws ServiceException;

}
