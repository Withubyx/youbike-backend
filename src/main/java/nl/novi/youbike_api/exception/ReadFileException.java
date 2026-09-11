package nl.novi.youbike_api.exception;

public class ReadFileException extends RuntimeException {

    public ReadFileException() {
        super();
    }

    public ReadFileException(String message) {
        super(message);
    }

    public ReadFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
