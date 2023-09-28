package exceptions;

public class NoMapLineException extends Exception {
  public NoMapLineException() {
  }

  public NoMapLineException(String message) {
    super(message);
  }

  public NoMapLineException(String message, Throwable cause) {
    super(message, cause);
  }

  public NoMapLineException(Throwable cause) {
    super(cause);
  }

  public NoMapLineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
