package by.epam.club.controller;


import by.epam.club.dao.CloseStatementResultSet;
import by.epam.club.pool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/TakePictureFromDB/*")
public class TakePictureFromDB extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        CloseStatementResultSet closeStatementResultSet = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        String imageName = request.getPathInfo().substring(1);

        try (Connection con = connectionPool.takeConnection()) {
            closeStatementResultSet = new CloseStatementResultSet();
            if (imageName.contains("profile")) {
                String userId = imageName.substring(8, imageName.indexOf("."));
                st = con.prepareStatement("SELECT file from userinfo where user_id=?");
                st.setString(1, userId);
                rs = st.executeQuery();

            } else if (imageName.contains("article")) {
                String articleId = imageName.substring(8, imageName.indexOf("."));
                st = con.prepareStatement("SELECT file from picture where article_article_id=?");
                st.setString(1, articleId);
                rs = st.executeQuery();
            }else if (imageName.contains("type")) {
                String articleId = imageName.substring(5, imageName.indexOf("."));
                st = con.prepareStatement("SELECT file from type where news_id=?");
                st.setString(1, articleId);
                rs = st.executeQuery();
                System.out.println(articleId+ "articleID");
            }
            while (rs.next()) {
                System.out.println(imageName + " imageName");
                byte[] content = rs.getBytes("file");
                System.out.println(content+" every content");

                if (content!=null) {
                    System.out.println(content.toString()+" content");
                    response.setContentType(getServletContext().getMimeType(imageName));
                    response.setContentLength(content.length);
                    response.getOutputStream().write(content);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementResultSet.close(rs, st);
        }
    }
}
