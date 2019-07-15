package by.epam.club.tool;

import by.epam.club.exception.DaoException;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static by.epam.club.entity.Parameter.UNKNOWN_MISTAKE_MESSAGE;

/**
 * The class is for encryption any password in forty characters password for assurance
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class PasswordEncryption {
    /**
     *
     * @param encrypted what we will encrypt
     * @return password after encryption
     * @throws DaoException this exception needs for next catching this in methods of the logic
     */
    public String create(String encrypted) throws DaoException {
        MessageDigest messageDigest;
        byte[] bytesEncoded;
        BigInteger bigInteger;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(encrypted.getBytes(StandardCharsets.UTF_8));
            bytesEncoded = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new DaoException(UNKNOWN_MISTAKE_MESSAGE);
        }
        if (bytesEncoded != null) {
            bigInteger = new BigInteger(1, bytesEncoded);
            return bigInteger.toString(16);
        } else {
            return null;
        }
    }
}
