package by.epam.club.controller;


import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceTakeImage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TakePictureFromDB/*")
public class TakePictureFromDB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String imageName = request.getPathInfo().substring(1);
        ServiceTakeImage serviceTakeImage = new ServiceTakeImage();
        try {
            byte[] content = serviceTakeImage.takeImage(imageName);
            if (content != null) {
                response.setContentType(getServletContext().getMimeType(imageName));
                response.setContentLength(content.length);
                response.getOutputStream().write(content);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
