package by.epam.club.tool;

/**
 * The class is created for validation any input in the form on frontend of .jsp
 *
 * @author Maeuski Igor
 * @version 1.0
 */
// FIXME: 7/12/2019
public class CredentialValidator {
    /**
     * @param login for checking
     * @param password for checking
     * @return boolean information about true or not
     */
    public boolean isCorrectLoginPassword(String login, String password) {
        return isLogicSize(login) && isLogicSize(password);
    }

    public boolean isLogicSize(String pole) {
        if (pole != null) {
            return pole.length() < 46 && pole.length() > 3;
        } else return false;
    }
}
