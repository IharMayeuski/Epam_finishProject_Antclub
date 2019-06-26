package by.epam.club.dao.typenewsdao;

import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.dao.basedao.BaseUploadPic;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.DaoException;


import javax.servlet.http.Part;
import java.util.Comparator;
import java.util.List;

import static by.epam.club.dao.SqlQuery.*;
import static by.epam.club.entity.Parameter.TYPES_PARAM;

@SuppressWarnings("unchecked")
public class TypeNewsDaoImpl extends BaseDao implements TypeNewsDao {

    @Override
    public List<TypeNews> takeUndeletedTypes() throws DaoException {

        List<TypeNews> types;
        try {
            types = find(TYPE_FIND_ALL_UNDELETED.getQuery(), TYPES_PARAM);
            Comparator<TypeNews> comparator = Comparator.comparing(TypeNews::getTypeNews);
            types.sort(comparator);
            return types;
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void create(String type) throws DaoException {
        try {
            create(TYPE_INSERT_NEW.getQuery(), type);
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }    }

    @Override
    public List<TypeNews> takeAllTypes() throws DaoException {
        List<TypeNews> types;
        try {
            types = find(TYPE_FIND_ALL.getQuery(), TYPES_PARAM);
            Comparator<TypeNews> comparator = Comparator.comparing(TypeNews::getTypeNews);
            types.sort(comparator);
            return types;
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }


    public TypeNews findType(String text, String table) throws DaoException {
        List<TypeNews> types;
        try {
            types = find(TYPE_FIND_ONE.getQuery(), table, text);
            return types.get(0);
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public void putPictureToType(String idForBase, Part part) throws DaoException {
        BaseUploadPic baseUploadPic = new BaseUploadPic();
        try {
            baseUploadPic.uploadPicture(TYPE_UPDATE_PIC.getQuery(),part,idForBase);
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }
}


