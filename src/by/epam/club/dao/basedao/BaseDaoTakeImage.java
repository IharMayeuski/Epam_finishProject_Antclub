package by.epam.club.dao.basedao;

import by.epam.club.exception.DaoException;
import by.epam.club.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.club.entity.Parameter.FILE_PARAM;
import static by.epam.club.entity.Parameter.SQL_EXCEPTION_MESSAGE;

public class BaseDaoTakeImage extends BaseDao {

    public byte[] takeImage(String query, String id) throws DaoException {
        ResultSet rs = null;
        PreparedStatement st = null;
        byte[] content = null;
        try (Connection con = ConnectionPool.getInstance().takeConnection()) {
            st = con.prepareStatement(query);
            st.setString(1, id);
            rs = st.executeQuery();
            if (rs != null && rs.next()) {
                content = rs.getBytes(FILE_PARAM);
                return content;
            }
        } catch (SQLException e) {
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        } finally {
            closeResources(rs, st);
        }
        return content;
    }
}

