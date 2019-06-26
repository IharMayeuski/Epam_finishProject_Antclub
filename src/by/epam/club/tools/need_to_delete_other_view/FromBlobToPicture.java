package by.epam.club.tools.need_to_delete_other_view;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

public class FromBlobToPicture {
    public void create(Blob blob){
        try{
            InputStream inputStream = blob.getBinaryStream();
            byte[] buffer = new byte [inputStream.available()];
            inputStream.read(buffer);
            File file = new File("C:\\file1.jpg");
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(buffer);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
