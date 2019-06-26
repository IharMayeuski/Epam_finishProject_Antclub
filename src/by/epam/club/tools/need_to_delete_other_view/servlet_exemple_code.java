package by.epam.club.tools.need_to_delete_other_view;/*
package by.it.malyshev.project.java.controller;

import by.it.malyshev.project.java.connection.ConnectionCreator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



@WebServlet("/images/*")
public class ImageController extends HttpServlet {

    private static final String SQL_FIND = "SELECT imgfile FROM books WHERE books.ID = ?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imageName = req.getPathInfo().substring(1);
        String bookid=imageName.substring(11,imageName.indexOf("."));

        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND)) {

            statement.setString(1, bookid);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    byte[] content = resultSet.getBytes("imgfile");
                    resp.setContentType(getServletContext().getMimeType(imageName));
                    resp.setContentLength(content.length);
                    resp.getOutputStream().write(content);
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Something failed at SQL/DB level.", e);
        }
    }

}


*/
