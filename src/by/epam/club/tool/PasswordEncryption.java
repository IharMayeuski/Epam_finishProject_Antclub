package by.epam.club.tool;

import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
    public String create(String encrypted) throws DaoException {
        MessageDigest messageDigest ;
        byte[] bytesEncoded;
        BigInteger bigInteger;
        try{
            messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(encrypted.getBytes(StandardCharsets.UTF_8));
            bytesEncoded = messageDigest.digest();
        }catch (NoSuchAlgorithmException e){
            throw new DaoException(e);
        }
        if (bytesEncoded!=null){
            bigInteger = new BigInteger(1, bytesEncoded);
            return bigInteger.toString(16);
        }else {
            return null;
        }

    }
}
