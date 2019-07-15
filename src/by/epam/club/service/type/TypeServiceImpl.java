package by.epam.club.service.type;

import by.epam.club.dao.TransactionHelper;
import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.dao.typenewsdao.TypeNewsDao;
import by.epam.club.dao.typenewsdao.TypeNewsDaoImpl;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static by.epam.club.dao.SqlQuery.TYPE_FIND_ONE_BY_TYPE;
import static by.epam.club.dao.SqlQuery.TYPE_UPDATE_PIC;
import static by.epam.club.entity.Parameter.*;

    /**
     * Class of business logic for TypeNews
     *
     * @author Maeuski Igor
     * @version 1.0
     */

public class TypeServiceImpl extends BaseDao implements TypeService {
    private static final Logger LOGGER = LogManager.getLogger(TypeServiceImpl.class);
    /**
     * @return {@code List<TypeNews>} all not deleted TypeNews
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public List<TypeNews> takeAllUndeleted() throws ServiceException {
        TypeNewsDao typeNewsDao = new TypeNewsDaoImpl();
        try {
            return typeNewsDao.takeUndeletedTypes();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    /**
     * @return {@code List<TypeNews>} all  TypeNews
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public List<TypeNews> takeAll() throws ServiceException {
        TypeNewsDao typeNewsDao = new TypeNewsDaoImpl();
        try {
            return typeNewsDao.takeAllTypes();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @param text for put text of the new Type to data base
     * @param part for sending Part format to data base
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void createNewType(String text, Part part) throws ServiceException {
        TypeNewsDaoImpl typeNewsDaoImpl = new TypeNewsDaoImpl();
        TypeNews type;
        try {
            if (typeNewsDaoImpl.find(TYPE_FIND_ONE_BY_TYPE.getQuery(), TYPES_PARAM,text).size()>0){
                throw new ServiceException(WE_HAVE_ANT_IN_DB_MESSAGE);
            }
        } catch (DaoException e) {
           throw new ServiceException(e.getMessage());
        }
        TransactionHelper transactionHelper = new TransactionHelper();
        try {
            transactionHelper.beginTransaction(typeNewsDaoImpl);
            typeNewsDaoImpl.create(text);
            type = typeNewsDaoImpl.findType(text, TYPES_PARAM);
            String idForBase = Long.toString(type.getId());
            InputStream is = part.getInputStream();
            typeNewsDaoImpl.uploadPic(TYPE_UPDATE_PIC.getQuery(), is, idForBase);
            transactionHelper.commit();
        } catch (DaoException | IOException e) {
            try {
                transactionHelper.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1.getMessage());
            }
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                transactionHelper.endTransaction();
            } catch (DaoException e) {
                LOGGER.error(UNKNOWN_MISTAKE_MESSAGE);
            }
        }
    }
    /**
     * @param typeNewsId for take TypeNews from data base by id
     * @return {@code TypeNews} from data base by id
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public TypeNews takeOne(int typeNewsId) throws ServiceException {
        TypeNewsDao typeNewsDao = new TypeNewsDaoImpl();
        try {
            return typeNewsDao.takeOne(typeNewsId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}

