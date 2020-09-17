package duke;

public class ErrorTypeManager {

    // Constants for error types
    public static final String ERROR_UNKNOWN_COMMAND = "error_command";
    public static final String ERROR_TODO_EMPTY_DESCRIPTION = "error_todo_empty_description";
    public static final String ERROR_EVENT_EMPTY_DESCRIPTION = "error_event_empty_description";
    public static final String ERROR_EVENT_WRONG_FORMAT = "error_event_wrong_format";
    public static final String ERROR_DEADLINE_EMPTY_DESCRIPTION = "error_deadline_empty_description";
    public static final String ERROR_DEADLINE_WRONG_FORMAT = "error_deadline_wrong_format";
    public static final String ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION
            = "error_markTaskDone_empty_description";
    public static final String ERROR_MARKTASKASDONE_WRONG_INDEX = "error_markTaskAsDone_wrong_index";
    public static final String ERROR_MARKTASKASDONE_NOT_NUMBER = "error_markTaskAsDone_not_number";
    public static final String ERROR_DELETETASK_EMPTY_DESCRIPTION
            = "error_delete_empty_description";
    public static final String ERROR_DELETETASK_NOT_NUMBER = "error_deleteTask_not_number";
    public static final String ERROR_DELETETASK_WRONG_INDEX = "error_deleteTask_wrong_index";
}
