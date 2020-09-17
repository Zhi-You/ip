package duke;

import duke.task.Task;

public class DisplayManager {

    private static final String DIVIDER = "________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke" + System.lineSeparator()
            + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // Duke Error messages
    private static final String SAD_FACE_EMOJI = "\u2639";
    private static final String ERROR_COMMAND_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_TODO_EMPTY_DESCRIPTION_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The description of a todo cannot be empty.";
    private static final String ERROR_EVENT_EMPTY_DESCRIPTION_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The description of an event cannot be empty.";
    private static final String ERROR_EVENT_WRONG_FORMAT_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The format to enter in an event task is \"<task> /at <event's time>\"";
    private static final String ERROR_DEADLINE_EMPTY_DESCRIPTION_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The description of a deadline cannot be empty.";
    private static final String ERROR_DEADLINE_WRONG_FORMAT_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The format to enter in a deadline task is \"<task> /by <deadline>\"";
    private static final String ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The description of a done cannot be empty.";
    private static final String ERROR_MARKTASKASDONE_WRONG_INDEX_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The task to mark as done must be an existing task";
    private static final String ERROR_MARKTASKASDONE_NOT_NUMBER_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! Use numbers to specify which task to be marked as done";
    private static final String ERROR_UNKNOWN_TASK_INDICATOR_MESSAGE =
            "Unidentified task type! Load file fail.";


    // File Error message
    private static final String FILE_ERROR_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! Something is wrong with the save file. Please check again";

    /* duke.DisplayManager methods */

    // Prints horizontal lines to wrap output for a clearer display
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    // Prints message to user wrapped with horizontal lines for clear display
    public static void printMessageToUser(String message) {
        printDivider();
        System.out.println(message);
        printDivider();
    }

    // Prints message to indicate user has added a task
    public static void printTaskAddedMessage(Task task) {
        printMessageToUser("Got it. I've added this task:" + System.lineSeparator()
                + "  " + task + System.lineSeparator()
                + "Now you have " + Task.getTaskCount()
                + (Task.getTaskCount() == 1? " task in the list." : " tasks in the list."));
    }

    // Prints message to indicate user has marked a task as done
    public static void printMarkAsDoneMessage(Task task) {
        printMessageToUser("Nice! I've marked this task as done: "
                + System.lineSeparator() + "  " + task);
    }

    // Prints duke.Duke's hello message along with his logo
    public static void printDukeHelloMessage() {
        System.out.println("Hello from\n" + DUKE_LOGO);
    }

    // Prints welcome message to user
    public static void printWelcomeMessage() {
        printMessageToUser(WELCOME_MESSAGE);
    }

    // Prints exit message to user
    public static void printExitMessage() {
        printMessageToUser(EXIT_MESSAGE);
    }

    // Prints list out for user when list command is received.
    public static void printList(Task[] tasks) {
        printDivider();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        printDivider();
    }

    // Prints different error messages for different exceptions encountered
    public static void printDukeErrorMessage(String errorType) {
        switch (errorType) {
        case ErrorTypeManager.ERROR_UNKNOWN_COMMAND:
            printMessageToUser(ERROR_COMMAND_MESSAGE);
            break;
        case ErrorTypeManager.ERROR_TODO_EMPTY_DESCRIPTION:
            printMessageToUser(ERROR_TODO_EMPTY_DESCRIPTION_MESSAGE);
            break;
        case ErrorTypeManager.ERROR_EVENT_EMPTY_DESCRIPTION:
            printMessageToUser(ERROR_EVENT_EMPTY_DESCRIPTION_MESSAGE);
            break;
        case ErrorTypeManager.ERROR_EVENT_WRONG_FORMAT:
            printMessageToUser(ERROR_EVENT_WRONG_FORMAT_MESSAGE);
            break;
        case ErrorTypeManager.ERROR_DEADLINE_EMPTY_DESCRIPTION:
            printMessageToUser(ERROR_DEADLINE_EMPTY_DESCRIPTION_MESSAGE);
            break;
        case ErrorTypeManager.ERROR_DEADLINE_WRONG_FORMAT:
            printMessageToUser(ERROR_DEADLINE_WRONG_FORMAT_MESSAGE);
            break;
        case ErrorTypeManager.ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION:
            printMessageToUser(ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION_MESSAGE);
            break;
        case ErrorTypeManager.ERROR_MARKTASKASDONE_WRONG_INDEX:
            printMessageToUser(ERROR_MARKTASKASDONE_WRONG_INDEX_MESSAGE);
            break;
        case ErrorTypeManager.ERROR_MARKTASKASDONE_NOT_NUMBER:
            printMessageToUser(ERROR_MARKTASKASDONE_NOT_NUMBER_MESSAGE);
            break;
        case ErrorTypeManager.ERROR_UNKNOWN_TASK_INDICATOR:
            printMessageToUser(ERROR_UNKNOWN_TASK_INDICATOR_MESSAGE);
            break;
        default:
            printMessageToUser("Unidentified error.");
            break;
        }
    }

    // Whenever there is an issue saving or loading from file
    public static void printFileErrorMessage() {
        printMessageToUser(FILE_ERROR_MESSAGE);
    }
}
