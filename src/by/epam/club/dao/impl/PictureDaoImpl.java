package by.epam.club.dao.impl;

import by.epam.club.dao.PictureDao;
import by.epam.club.dao.pool.ConnectionPool;
import by.epam.club.dao.pool.ConnectionProxy;
import by.epam.club.exception.ConnectionPoolException;
import by.epam.club.exception.DaoException;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PictureDaoImpl implements PictureDao {
    private PreparedStatement st;
    private int rs;
    private ConnectionProxy con = null;
    private ConnectionPool connectionPool = null;
    private InputStream inputStream = null;

    @Override
    public boolean create(String name, Part part,  String picOwner, int idOwner) throws DaoException {
        boolean resault = false;
        String owner= "";
        final int DEFAULT_VALUE = 1;

        if (picOwner.equals("comment")) owner="comment_comment_id";
        if (picOwner.equals("letter")) owner="letter_letter_id";
        if (picOwner.equals("article")) owner="article_article_id";
        if (picOwner.equals("userInfo")) owner="user_info_id";

        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            st = con.prepareStatement("INSERT INTO picture (file_name, file, "+
                    owner+", article_id, banned_block_id) VALUES (?,?,?,?)");

            inputStream = part.getInputStream();

            st.setString(1, name);
            st.setBlob(2, inputStream);
            st.setInt(3,idOwner);
            st.setInt(4,DEFAULT_VALUE);
            rs = st.executeUpdate();
            con.commit();
            resault = true;
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
        return resault;
    }
}
