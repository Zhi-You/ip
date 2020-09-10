// Using errorType string to differentiate what type of error we are facing with
// To allow DisplayManager to know what message to print through a switch statement
// Note: initially wanted to use an enum but that was not an option anymore as we should pass
// in a string instead.
public class DukeException extends Exception{
    DukeException(String errorType) {
        super(errorType);
    }
}
