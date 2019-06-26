package by.epam.club.dao.picturedao;

import by.epam.club.dao.CloseStatementResultSet;
import by.epam.club.entity.Parameter;
import by.epam.club.pool.ConnectionPool;
import by.epam.club.entity.Picture;
import by.epam.club.exception.DaoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.epam.club.dao.SqlQuery.*;

public class PictureDaoImpl implements PictureDao {
    private PreparedStatement st;
    private Connection con = null;
    private ConnectionPool connectionPool = null;
    private CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();

    @Override//todo скорее всего лишний метод, нужно построить работу через сервлет
    public boolean create(String name, String filePath, long idOwner) throws DaoException {
        boolean result = false;
        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            st = con.prepareStatement(PICTURE_INSERT_NEW.getQuery());
            InputStream inputStream = new FileInputStream(new File(filePath));
            // inputStream = part.getInputStream();

            st.setString(1, name);
            st.setBlob(2, inputStream);
            st.setLong(3, idOwner);
            st.executeUpdate();
            con.commit();
            result = true;
        } catch (FileNotFoundException e) {
            throw new DaoException("can't find file");
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e1);
            }
        } finally {
            closeStatementResultSet.close(st);
            connectionPool.returnConnection(con);
        }
        return result;
    }

    @Override
    public boolean markDelete(Picture picture) throws DaoException {
        boolean value = false;
        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            st = con.prepareStatement(PICTURE_MARK_DELETE.getQuery());
            st.setLong(1, picture.getId());
            st.executeUpdate();
            con.commit();
            value = true;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e1);
            }
        } finally {
            closeStatementResultSet.close(st);
            connectionPool.returnConnection(con);
        }
        return value;
    }


    @Override
    public boolean markBannedUnbanned(Picture picture) throws DaoException {
        boolean value = false;
        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            if (picture.getBanned().equals(Parameter.BANNED_PARAM)) {
                st = con.prepareStatement(PICTURE_MARK_UNBANNED.getQuery());
            } else {
                st = con.prepareStatement(PICTURE_MARK_BANNED.getQuery());
            }
            st.setLong(1, picture.getId());
            st.executeUpdate();
            con.commit();
            value = true;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e1);
            }
        } finally {
            closeStatementResultSet.close(st);
            connectionPool.returnConnection(con);
        }
        return value;
    }
}
