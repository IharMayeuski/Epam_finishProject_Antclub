package by.epam.club.exception;

public class NotDBDriverException extends RuntimeException {

    public NotDBDriverException(String message, Exception e) {
        super(message, e);

    }

}
