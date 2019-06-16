package by.epam.club.validation;

public class CredentialValidator {
    public boolean isCorrectLoginPassword(String login, String password) {
        return isLogicCorrect(login) && isLogicCorrect(password);
    }

    public boolean isLogicCorrect(String pole) {
        return pole.length() < 46 && pole.length() > 3;
    }


}
