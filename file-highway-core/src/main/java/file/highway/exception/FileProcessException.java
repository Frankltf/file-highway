package file.highway.exception;

/**
 * @Intro
 * @Author liutengfei
 */
public class FileProcessException extends RuntimeException {

    public FileProcessException() {
    }

    public FileProcessException(String message) {
        super(message);
    }

    public FileProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
