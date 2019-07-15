package by.epam.club.dao.picturedao;

import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.exception.DaoException;

import static by.epam.club.dao.SqlQuery.*;
/**
 * Class of level dao for working with Picture
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class PictureDaoImpl extends BaseDao implements PictureDao {
    /**
     *
     * @param pictureId for Mark picture in status deleted
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void delete(String pictureId) throws DaoException {
            create(PICTURE_MARK_DELETE.getQuery(), pictureId);
    }
}
