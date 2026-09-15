package nl.novi.youbike_api.exception;

public class MissingFileException extends RuntimeException {
    public MissingFileException(String message) {
        super(message);
    }
}
