package by.epam.club.service.type;

import by.epam.club.entity.TypeNews;
import by.epam.club.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

    /**
     * Interface of business logic for TypeNews
     *
     * @author Maeuski Igor
     * @version 1.0
     */

public interface TypeService {

    List<TypeNews> takeAllUndeleted() throws ServiceException;

    List<TypeNews> takeAll() throws ServiceException;

    void createNewType(String text, Part part) throws ServiceException;

    TypeNews takeOne(int typeNewsId) throws ServiceException;
}
