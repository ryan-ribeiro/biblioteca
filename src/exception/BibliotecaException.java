package exception;

public class BibliotecaException extends RuntimeException{
    public BibliotecaException(String message) {
        super(message);
    }
    public BibliotecaException(String message, Throwable cause) {

        super(message, cause);
    }
    public BibliotecaException(Throwable cause) {

        super(cause);
    }
}
