package by.epam.club.tool;

public class CredentialValidator {
    public boolean isCorrectLoginPassword(String login, String password) {

        return isLogicSize(login) && isLogicSize(password);
    }

    public boolean isLogicSize(String pole) {
        if (pole != null) {
            return pole.length() < 46 && pole.length() > 3;
        } else return false;
    }
}
