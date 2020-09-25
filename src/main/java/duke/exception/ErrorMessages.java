package duke.exception;

/**
 * Represents a collection of error messages to print out to the user
 * based on the type of error that occurred.
 */
public class ErrorMessages {
    // Duke related error messages
    public static final String SAD_FACE_EMOJI = "\u2639";
    public static final String ERROR_COMMAND_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_TODO_EMPTY_DESCRIPTION_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The description of a todo cannot be empty.";
    public static final String ERROR_EVENT_EMPTY_DESCRIPTION_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The description of an event cannot be empty.";
    public static final String ERROR_EVENT_WRONG_FORMAT_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The format to enter in an event task is \"event <task> /at <event's time>\"";
    public static final String ERROR_DEADLINE_EMPTY_DESCRIPTION_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The description of a deadline cannot be empty.";
    public static final String ERROR_DEADLINE_WRONG_FORMAT_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The format to enter in a deadline task is \"deadline <task> /by <deadline>\"";
    public static final String ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The description of the done command cannot be empty.";
    public static final String ERROR_MARKTASKASDONE_WRONG_INDEX_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The task to mark as done must be an existing task.";
    public static final String ERROR_MARKTASKASDONE_NOT_NUMBER_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! Use numbers to specify which task to be marked as done.";
    public static final String ERROR_DELETETASK_EMPTY_DESCRIPTION_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The description of the delete command cannot be empty.";
    public static final String ERROR_DELETETASK_NOT_NUMBER_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! Use numbers to specify which task to be deleted.";
    public static final String ERROR_DELETETASK_WRONG_INDEX_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The task to be deleted must be an existing task.";
    public static final String UNEXPECTED_ERROR = SAD_FACE_EMOJI
            + " OOPS!!! An unidentified error has occurred! Please take note!";
    public static final String ERROR_UNKNOWN_TASK_INDICATOR_MESSAGE =  SAD_FACE_EMOJI
            + " OOPS!!! Unidentified task type! Load file fail.";

    // File Error message
    public static final String FILE_ERROR_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! Something is wrong with accessing the save file. Please check again";
}
