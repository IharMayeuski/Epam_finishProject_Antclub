package by.epam.club.controller;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.dao.impl.SqlQuery;
import by.epam.club.entity.Parameter;
import by.epam.club.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "/UploadImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 1024 * 1024) //1 MB
public class UploadImage extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(UploadImage.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part part = request.getPart(Parameter.IMAGE_PARAM);
        String userId = request.getParameter(Parameter.USER_ID_PARAM);
        String newLocale = (String) request.getSession().getAttribute(Parameter.LOCAL_PARAM);
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        if (part != null) {
            try (Connection con = connectionPool.takeConnection()) {
                PreparedStatement ps = con.prepareStatement(SqlQuery.USER_UPDATE_PIC.getQuery());
                InputStream is = part.getInputStream();
                ps.setBlob(1, is);
                ps.setString(2, userId);
                ps.executeUpdate();
            } catch (SQLException e) {
                LOGGER.info(Parameter.SQL_EXCEPTION_PARAM, e.getMessage());
                request.setAttribute(Parameter.ERROR_PARAM, MessageManager.getProperty(e.getMessage(), newLocale));
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher( ConfigurationManager.getProperty(Parameter.DEFAULT_PAGE));
        dispatcher.forward(request, response);
    }
}
