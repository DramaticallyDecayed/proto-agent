package dd.translator;

/**
 * Used to wrap checked exceptions in lambda expressions
 * <p>
 * Created by Sergey on 15.05.2016.
 */
public class WrappedTranslatorException extends RuntimeException {

    public WrappedTranslatorException(String msg) {
        super(msg);
    }

    public WrappedTranslatorException(String msg, Throwable t) {
        super(msg, t);
    }

}
