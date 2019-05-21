package by.epam.club.exception;

public class ConnectionProxyException extends Throwable {
    private static final long serialVersionUID = 1L;
    public ConnectionProxyException (String message, Exception e) {
        super(message, e);
    }

    public ConnectionProxyException() {
        super();
    }

    public ConnectionProxyException(String message) {
        super(message);
    }

    public ConnectionProxyException (Exception e) {
        super(e);
    }
}
