package by.epam.club.dao.impl;

import by.epam.club.dao.PictureDao;
import by.epam.club.dao.pool.ConnectionPool;
import by.epam.club.dao.pool.ConnectionProxy;
import by.epam.club.entity.Picture;
import by.epam.club.exception.ConnectionPoolException;
import by.epam.club.exception.DaoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.epam.club.dao.impl.SqlFunction.*;

public class PictureDaoImpl implements PictureDao {
    private PreparedStatement st;
    private int rs;
    private ConnectionProxy con = null;
    private ConnectionPool connectionPool = null;
    private InputStream inputStream = null;

    @Override
    public boolean create(String name, String filePath, int idOwner) throws DaoException {
        boolean result;

        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            st = con.prepareStatement(PICTURE_INSERT_NEW.getQuery());

           // inputStream = part.getInputStream();
            inputStream = new FileInputStream(new File(filePath));

            st.setString(1, name);
            st.setBlob(2, inputStream);
            st.setInt(3,idOwner);
            rs = st.executeUpdate();
            con.commit();
            result = true;
        } catch ( NullPointerException|ConnectionPoolException | IOException | SQLException e) {
            try {
                con.rollback();
            } catch (SQLException message) {
                throw new DaoException(message);
            }
            throw new DaoException(e);
        } finally {
            if (connectionPool != null) {
                try {
                    connectionPool.returnConnection(con);
                } catch (ConnectionPoolException e) {
                    throw new DaoException(e);//todo уточнить, что делать в файнали со throw
                }
            }
        }
        return result;
    }

    @Override
    public boolean delete(Picture picture) throws DaoException {
        boolean value = false;
        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            st = con.prepareStatement(PICTURE_DELETE.getQuery());
            st.setInt(1, picture.getId());
            st.executeUpdate();
            con.commit();
            value = true;
        } catch (ConnectionPoolException | SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e1);
            }
        } finally {//todo уточнить, что делать в файнали со throw
            if (connectionPool != null) {
                try {
                    connectionPool.returnConnection(con);
                } catch (ConnectionPoolException e) {
                    throw new DaoException(e);
                }
            }
        }
        return value;
    }
}
