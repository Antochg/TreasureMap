package exceptions;

public class WrongLineTypeException extends Exception {
  public WrongLineTypeException() {
  }

  public WrongLineTypeException(String message) {
    super(message);
  }

  public WrongLineTypeException(String message, Throwable cause) {
    super(message, cause);
  }

  public WrongLineTypeException(Throwable cause) {
    super(cause);
  }

  public WrongLineTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
