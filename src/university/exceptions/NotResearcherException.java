package university.exceptions;

/**
 * Thrown when an operation requires the target to implement the Researcher
 * interface but the given object does not.
 */
public class NotResearcherException extends RuntimeException {

    /**
     * Constructs a new NotResearcherException with the given detail message.
     *
     * @param message description of the invalid operation
     */
    public NotResearcherException(String message) {
        super(message);
    }
}
