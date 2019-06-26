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

import static by.epam.club.dao.SqlQuery.TYPE_FIND_ONE;
import static by.epam.club.dao.SqlQuery.TYPE_UPDATE_PIC;
import static by.epam.club.entity.Parameter.*;

public class TypeServiceImpl extends BaseDao implements TypeService {
    private static final Logger LOGGER = LogManager.getLogger(TypeServiceImpl.class);
    @Override
    public List<TypeNews> takeAllUndeleted() throws ServiceException {
        TypeNewsDao typeNewsDao = new TypeNewsDaoImpl();
        try {
            return typeNewsDao.takeUndeletedTypes();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<TypeNews> takeAll() throws ServiceException {
        TypeNewsDao typeNewsDao = new TypeNewsDaoImpl();
        try {
            return typeNewsDao.takeAllTypes();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }


    @Override
    public void createNewType(String text, Part part) throws ServiceException {
        TypeNewsDaoImpl typeNewsDaoImpl = new TypeNewsDaoImpl();
        TypeNews type;

        try {
            if (typeNewsDaoImpl.find(TYPE_FIND_ONE.getQuery(), TYPES_PARAM,text).size()>0){
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
            typeNewsDaoImpl.uploadPicInTransaction(TYPE_UPDATE_PIC.getQuery(), is, idForBase);
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
}

