/*
package by.epam.club.service.validation;

import by.epam.generaltask.dao.impl.SqlDao;
import by.epam.generaltask.exception.ServiceException;

import java.sql.*;

public class LoginValidator {

    private static final String QUERY_CHECK_CREDENTIONAL = "SELECT email FROM User WHERE email=?";
 private static final ConnectionPoolDecorator pool = ConnectionPoolDecorator.getInstance();


    public static boolean isCorrect(String log) throws ServiceException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean ret = false;
        try {
            con = DriverManager.getConnection(url, login, password);
            st = con.prepareStatement(QUERY_CHECK_CREDENTIONAL);
            st.setString(1, log);
            rs = st.executeQuery();
            if (!rs.next()) {
                ret = true;
            }
        } catch (SQLException e) {
            System.out.println("there is login like your");
            throw new ServiceException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new ServiceException(e);
            }

            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                throw new ServiceException(e);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new ServiceException(e);
            }
        }
        return ret;
    }

}
*/
