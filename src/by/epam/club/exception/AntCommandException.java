package by.epam.club.exception;

public class AntCommandException extends Throwable {

    private static final long serialVersionUID = 1L;

    public AntCommandException() {
        super();
    }

    public AntCommandException(String message) {
        super(message);
    }

    public AntCommandException(Exception e) {
        super(e);
    }

    public AntCommandException(String message, Exception e) {
        super(message, e);
    }

}
