package by.epam.club.service.validation;

public class CredentialValidator {
	public static boolean  isCorrect (String login, String password) {
		return isLogicCorrect(login) && isPasswordCorrect(password);
	}
	
	private static boolean isLogicCorrect (String login) {
		return login.length() > 3;
	}
	
	private static boolean isPasswordCorrect (String password) {
		return password.length() > 3;
	}
	
	
}
