/*
package by.epam.club.tool;

import java.sql.Connection;

public class FromPictureToBlob {
    public void create(long userId, String fileName){
        public void updateUserImage(long userId, String fileName){
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement preparedStatement=null;
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(fileName));
                preparedStatement = connection.prepareStatement(SqlUserQuery.SQL_UPDATE_USER_IMAGE.getQuery());
                preparedStatement.setBlob(1, fileInputStream);
                preparedStatement.setLong(2, userId);
                preparedStatement.executeUpdate();
            } catch (SQLException | FileNotFoundException e) {
                throw new DaoException("SQLException in method updateUserImage", e);
            } finally {

                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DaoException("SQLException in method updateUserImage close connection / preparedStatement", e);
                }
            }
        }
    }
}
*/
