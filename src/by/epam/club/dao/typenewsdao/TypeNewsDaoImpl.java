package by.epam.club.dao.typenewsdao;

import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.DaoException;

import java.util.Comparator;
import java.util.List;

import static by.epam.club.dao.SqlQuery.*;
import static by.epam.club.entity.Parameter.TYPES_PARAM;
import static by.epam.club.entity.Parameter.UNKNOWN_MISTAKE_MESSAGE;

/**
 * Class of level dao for working with TypeNews
 *
 * @author Maeuski Igor
 * @version 1.0
 */

@SuppressWarnings("unchecked")
public class TypeNewsDaoImpl extends BaseDao implements TypeNewsDao {
    /**
     * @return {@code List<TypeNews} that were founded in the base like undeleted types
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public List<TypeNews> takeUndeletedTypes() throws DaoException {
        List<TypeNews> types;
        types = find(TYPE_FIND_ALL_UNDELETED.getQuery(), TYPES_PARAM);
        Comparator<TypeNews> comparator = Comparator.comparing(TypeNews::getTypeNews);
        types.sort(comparator);
        return types;
    }

    /**
     * @param type is the text of new TypeNews
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void create(String type) throws DaoException {
        create(TYPE_INSERT_NEW.getQuery(), type);
    }

    /**
     * @return {@code List<TypeNews} that were founded in the base all types
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public List<TypeNews> takeAllTypes() throws DaoException {
        List<TypeNews> types;
        types = find(TYPE_FIND_ALL.getQuery(), TYPES_PARAM);
        Comparator<TypeNews> comparator = Comparator.comparing(TypeNews::getTypeNews);
        types.sort(comparator);
        return types;
    }

    /**
     * @param type it is id of Type fore searching
     * @return {@code TypeNews}
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public TypeNews takeOne(int type) throws DaoException {
        List<TypeNews> list = find(TYPE_FIND_ONE_BY_ID.getQuery(), TYPES_PARAM, Integer.toString(type));
        if (list.size() > 0) {
            return list.get(0);
        } else throw new DaoException(UNKNOWN_MISTAKE_MESSAGE);
    }

    /**
     * @param typeNews it is typeNews id
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void markUndeleted(int typeNews) throws DaoException {
        create(TYPE_MARK_UNDELETED.getQuery(), Integer.toString(typeNews));
    }

    /**
     * @param typeNews it is typeNews id
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void markDeleted(int typeNews) throws DaoException {
        create(TYPE_MARK_DELETED.getQuery(), Integer.toString(typeNews));
    }

    /**
     * @param text  for searching unique typeNews name
     * @param table for understanding EntityCreator what type of Entity it will create
     * @return one Type of TypeNews
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    public TypeNews findType(String text, String table) throws DaoException {
        List<TypeNews> types;
        types = find(TYPE_FIND_ONE_BY_TYPE.getQuery(), table, text);
        if (types.size() > 0) {
            return types.get(0);
        } else throw new DaoException(UNKNOWN_MISTAKE_MESSAGE);
    }
}


