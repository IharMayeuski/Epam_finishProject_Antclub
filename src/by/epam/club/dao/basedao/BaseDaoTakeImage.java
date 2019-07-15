package by.epam.club.dao.basedao;

import by.epam.club.exception.DaoException;
import by.epam.club.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.club.entity.Parameter.FILE_PARAM;
import static by.epam.club.entity.Parameter.SQL_EXCEPTION_MESSAGE;
/**
 * Class for upload picture to data base
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public class BaseDaoTakeImage extends BaseDao {
    /**
     *
     * @param query real SQL query in script
     * @param id of picture in the system
     * @return byte code for sending it on the page
     * @throws DaoException for catching it on the logic level in the case of exception
     */
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

