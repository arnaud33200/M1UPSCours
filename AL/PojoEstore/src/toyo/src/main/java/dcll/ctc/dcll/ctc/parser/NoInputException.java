package dcll.ctc.dcll.ctc.parser;

/**
 * @author alpha oumar binta diallo
 */
@SuppressWarnings("serial")
public class NoInputException extends Exception {
    /**
     * Default constructor.
     */
    public NoInputException() {
    }

    /**
     * @param message
     *            the message to show.
     */
    public NoInputException(final String message) {
        super(message);
    }
}
