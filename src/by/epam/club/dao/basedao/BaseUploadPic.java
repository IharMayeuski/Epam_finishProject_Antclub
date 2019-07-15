package by.epam.club.dao.basedao;

import by.epam.club.exception.DaoException;
import by.epam.club.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.epam.club.entity.Parameter.SQL_EXCEPTION_MESSAGE;
import static by.epam.club.entity.Parameter.UNKNOWN_MISTAKE_MESSAGE;
/**
 * Class for upload picture to data base
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public class BaseUploadPic extends BaseDao {

    private static final Logger LOGGER = LogManager.getLogger(BaseUploadPic.class);

    /**
     *
     * @param query SQL query for script
     * @param part out picture
     * @param id user's id
     * @throws DaoException for catching it on the logic level in the case of exception
     */

    public void uploadPicture(String query, Part part, String id) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        PreparedStatement ps = null;
        try (Connection con = connectionPool.takeConnection()) {
            ps = con.prepareStatement(query);
            InputStream is = part.getInputStream();
            ps.setBlob(1, is);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info(SQL_EXCEPTION_MESSAGE, e.getMessage());
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        } catch (IOException e) {
            LOGGER.error(UNKNOWN_MISTAKE_MESSAGE, e.getMessage());
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        } finally {
            closeResources(ps);
        }
    }
}
