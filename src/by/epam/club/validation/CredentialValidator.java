package by.epam.club.validation;

public class CredentialValidator {
	public boolean isCorrectLoginPassword(String login, String password) {
		return isLogicCorrect(login) && isPasswordCorrect(password);
	}

	public boolean isCorrectLengthPole (String pole){
		return pole.length()<45;
	}
	
	private boolean isLogicCorrect (String login) {
		return (login.length()<45)&&(login.length() > 3);
	}
	
	private boolean isPasswordCorrect (String password) {
		return (password.length() < 45)&&(password.length() > 3);
	}
	
}
