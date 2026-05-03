package university.exceptions;

/**
 * Thrown when a researcher's h-index is too low to satisfy a requirement
 * (e.g., when assigning a supervisor who must have h-index >= 3).
 */
public class LowHIndexException extends RuntimeException {

    /**
     * Constructs a new LowHIndexException with the given detail message.
     *
     * @param message description of why the h-index is insufficient
     */
    public LowHIndexException(String message) {
        super(message);
    }
}
