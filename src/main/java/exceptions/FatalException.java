package exceptions;

public class FatalException  extends RuntimeException{
    public FatalException(String message) {
        super(message);
    }
}
