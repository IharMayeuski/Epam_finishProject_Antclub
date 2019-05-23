/*
package by.epam.generaltask.controller;

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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "/UploadImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,maxFileSize = 1024 * 1024, maxRequestSize = 1024 * 1024) //1 MB
public class UploadImage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("i am here");

        String connectionURL = "jdbc:mysql://localhost:3306/epam1?useSSL=false";
        String user = "root";
        String pass = "12345";
        PrintWriter out = response.getWriter();
        int result = 0;
        Connection con;

        Part part = request.getPart("image");
        String name = request.getParameter("name");

        if (part != null) {
            try {
                Class.forName("org.gjt.mm.mysql.Driver");
                con = DriverManager.getConnection(connectionURL, user, pass);

                System.out.println(con.toString());
                PreparedStatement ps = con.prepareStatement("insert into pic(name,file) values(?,?)");
                InputStream is = part.getInputStream();
                System.out.println(is.toString()+"is inputstream");

                ps.setString(1,name);
                ps.setBlob(2, is);

                result = ps.executeUpdate();
                if (result>0){
                    System.out.println("<h1> image inserted succussfully</h1>");
                }
            }catch (Exception e){
                System.out.println(e);//logiiiiiiiiii
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/default.jsp");
        dispatcher.forward(request, response);


    }
}
*/
