package file.highway.exception;

/**
 * @Intro
 * @Author liutengfei
 */
public class ConnectedProcessException extends RuntimeException {
    public ConnectedProcessException() {
    }

    public ConnectedProcessException(String message) {
        super(message);
    }

    public ConnectedProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
