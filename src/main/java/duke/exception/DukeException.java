package duke.exception;

/**
 * Signals an error when running Duke.
 */
public class DukeException extends Exception {
    /**
     * Covers all the different type of exceptions that can occur when running Duke.
     * Uses <code>e.getMessage</code> to identify which specific error has occurred.
     *
     * @param errorType Identifies the specific error corresponding to one of the errors in ErrorTypeManager.
     */
    public DukeException(String errorType) {
        super(errorType);
    }
}
