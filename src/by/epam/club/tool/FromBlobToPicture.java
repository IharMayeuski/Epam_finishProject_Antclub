package by.epam.club.tool;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

public class FromBlobToPicture {
    public void create(Blob blob){
        try{
            InputStream inputStream = blob.getBinaryStream();
            byte[] buffer = new byte [inputStream.available()];
            inputStream.read(buffer);
            File targetFile = new File("file.jpg");
            OutputStream outputStream = new FileOutputStream(targetFile);
            outputStream.write(buffer);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
