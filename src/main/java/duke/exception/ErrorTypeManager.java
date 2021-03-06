package duke.exception;

/**
 * Container for error types that can occur when running Duke.
 */
public class ErrorTypeManager {
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
    public static final String ERROR_DELETE_TASK_EMPTY_DESCRIPTION
            = "error_delete_empty_description";
    public static final String ERROR_DELETE_TASK_NOT_NUMBER = "error_deleteTask_not_number";
    public static final String ERROR_DELETE_TASK_WRONG_INDEX = "error_deleteTask_wrong_index";
    public static final String ERROR_FIND_TASK_EMPTY_DESCRIPTION= "error_find_task_empty_description";
    public static final String ERROR_UNKNOWN_TASK_TYPE = "error_unknownTaskType";
    public static final String ERROR_NO_DATA_TO_LOAD = "error_no_data_to_load";
    public static final String ERROR_WITH_FILE = "error_with_file";
}
