package duke;

// Using errorType string to differentiate what type of error we are facing with
// To allow duke.DisplayManager to know what message to print through a switch statement
// Note: initially wanted to use an enum but that was not an option anymore as we should pass
// in a string instead to identify the type of error
public class DukeException extends Exception{
    public DukeException(String errorType) {
        super(errorType);
    }
}
