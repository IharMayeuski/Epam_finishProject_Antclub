package by.epam.club.service;

import by.epam.club.dao.basedao.BaseDaoTakeImage;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;

import static by.epam.club.dao.SqlQuery.*;
import static by.epam.club.entity.Parameter.*;

public class ServiceTakeImage {
    public byte[] takeImage(String imageName) throws ServiceException {
        BaseDaoTakeImage daoTakeImage = new BaseDaoTakeImage();
        byte[] content = null;
        try {
            if (imageName.contains(PROFILE_PARAM)) {
                String userId = imageName.substring(8, imageName.indexOf("."));
                content = daoTakeImage.takeImage(USER_SELECT_BLOB_FROM_INFO.getQuery(),userId);
            } else if (imageName.contains(ARTICLE_PARAM)) {
                String articleId = imageName.substring(8, imageName.indexOf("."));
                content = daoTakeImage.takeImage(PICTURE_SELECT_BLOB.getQuery(),articleId);
            } else if (imageName.contains(TYPE_PARAM)) {
                String typeId = imageName.substring(5, imageName.indexOf("."));
                content = daoTakeImage.takeImage(TYPE_SELECT_BLOB.getQuery(),typeId);
            }
            return content;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
