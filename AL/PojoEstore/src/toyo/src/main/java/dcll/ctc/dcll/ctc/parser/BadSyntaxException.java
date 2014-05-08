package dcll.ctc.dcll.ctc.parser;

/**
 * @author alpha oumar binta diallo
 *
 */
@SuppressWarnings("serial")
public class BadSyntaxException extends Exception {
    /**
     * default constructor.
     */
    public BadSyntaxException() { }

    /**
     * @param message the message to show.
     */
    public BadSyntaxException(final String message) {
        super(message);
    }
}
