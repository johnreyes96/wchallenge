package ar.com.wolox.wchallenge.exception;

public class DuplicateRegisterException extends RuntimeException {

    public DuplicateRegisterException() {
        super();
    }

    public DuplicateRegisterException(String message) {
        super(message);
    }

    public DuplicateRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateRegisterException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}