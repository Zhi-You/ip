package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.exception.ErrorMessages.ERROR_COMMAND_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_DEADLINE_EMPTY_DESCRIPTION_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_DEADLINE_WRONG_FORMAT_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_DELETE_TASK_EMPTY_DESCRIPTION_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_DELETE_TASK_NOT_NUMBER_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_DELETE_TASK_WRONG_INDEX_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_EVENT_EMPTY_DESCRIPTION_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_EVENT_WRONG_FORMAT_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_FIND_TASK_EMPTY_DESCRIPTION_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_MARKTASKASDONE_NOT_NUMBER_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_MARKTASKASDONE_WRONG_INDEX_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_TODO_EMPTY_DESCRIPTION_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_UNKNOWN_TASK_INDICATOR_MESSAGE;
import static duke.exception.ErrorMessages.FILE_ERROR_MESSAGE;
import static duke.exception.ErrorMessages.UNEXPECTED_ERROR;

import static duke.exception.ErrorTypeManager.ERROR_DEADLINE_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_DEADLINE_WRONG_FORMAT;
import static duke.exception.ErrorTypeManager.ERROR_DELETE_TASK_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_DELETE_TASK_NOT_NUMBER;
import static duke.exception.ErrorTypeManager.ERROR_DELETE_TASK_WRONG_INDEX;
import static duke.exception.ErrorTypeManager.ERROR_EVENT_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_EVENT_WRONG_FORMAT;
import static duke.exception.ErrorTypeManager.ERROR_FIND_TASK_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_MARKTASKASDONE_NOT_NUMBER;
import static duke.exception.ErrorTypeManager.ERROR_MARKTASKASDONE_WRONG_INDEX;
import static duke.exception.ErrorTypeManager.ERROR_TODO_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_UNKNOWN_COMMAND;
import static duke.exception.ErrorTypeManager.ERROR_UNKNOWN_TASK_TYPE;
import static duke.exception.ErrorTypeManager.ERROR_WITH_FILE;

public class Ui {
    private final String DIVIDER = "______________________________________________________";

    private final String WELCOME_MESSAGE = "Hello! I'm Duke" + System.lineSeparator()
            + "What can I do for you?";
    private final String NOTE_TO_USER = "(Available commands are: 'list', 'todo', 'event',"
            + System.lineSeparator() + "'deadline', 'done', 'delete', 'find' and 'bye')"
            + System.lineSeparator() + "(Note: Dates should be given in 'yyyy-mm-dd' format)";
    private final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private final String SUCCESSFUL_DATA_LOAD_MESSAGE = "(Note: "
            + "Your saved data was successfully loaded!)";
    private final String FAIL_DATA_LOAD_MESSAGE = "(Note: "
            + "Could not detect any saved data!)";


    private final String NO_MATCHING_TASK_MESSAGE = "There are no matching tasks in your list.";

    private final Scanner SCANNER = new Scanner(System.in);


    /**
     * Gets input from the user and returns it as a string.
     *
     * @return User input.
     */
    public String getUserInput() {
        return SCANNER.nextLine();
    }

    /**
     * Prints a horizontal line as a divider for clearer display of messages.
     */
    public void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints a message wrapped in horizontal lines for a clearer display.
     *
     * @param message Message to be printed to the user.
     */
    public void printMessageToUser(String message) {
        printDivider();
        System.out.println(message);
        printDivider();
    }

    private void printDukeHelloMessage() {
        System.out.println("Hello from\n" + DUKE_LOGO);
    }

    /**
     * Prints a note to user to help them get started with using Duke.
     */
    private void printNoteToUser() {
        System.out.println(NOTE_TO_USER);
    }

    /**
     * Prints a welcome message at the start of program execution that consists
     * of greetings, Duke's logo and a note to user to help them get started with using Duke.
     */
    public void printWelcomeMessage() {
        printDukeHelloMessage();
        printDivider();
        printNoteToUser();
        printMessageToUser(WELCOME_MESSAGE);
    }

    /**
     * Prints an exit message upon the termination of the program.
     */
    public void printExitMessage() {
        printMessageToUser(EXIT_MESSAGE);
    }

    /**
     * Prints a message to notify the user that a specific task is successfully added to the
     * task list. Includes information of how many tasks are currently in the list.
     *
     * @param task Task being added to the task list.
     * @param numberOfTasks Number of tasks currently in the task list.
     */
    public void printTaskAddedMessage(Task task, int numberOfTasks) {
        printMessageToUser("Got it. I've added this task:" + System.lineSeparator()
                + "  " + task + System.lineSeparator()
                + printTaskCount(numberOfTasks));
    }

    /**
     * Prints a message to notify the user that a specific task is successfully deleted from the
     * task list. Includes information of how many tasks are remaining in the list.
     *
     * @param task Task being deleted from the task list.
     * @param numberOfTasks Number of tasks remaining in the task list.
     */
    public void printTaskDeletedMessage (Task task, int numberOfTasks) {
        printMessageToUser("Noted. I've removed this task: " + System.lineSeparator()
                + "  " + task + System.lineSeparator()
                + printTaskCount(numberOfTasks - 1));
    }

    /**
     * Returns a string containing information about the number of tasks currently in the task list.
     *
     * @param numberOfTasks Number of tasks currently in the task list.
     */
    public String printTaskCount(int numberOfTasks) {
        return("Now you have " + numberOfTasks +
                (numberOfTasks == 1 ? " task in the list." : " tasks in the list."));
    }

    /**
     * Prints a message to notify the user that a specific task is successfully marked as done.
     *
     * @param task Task being marked as done.
     */
    public void printMarkAsDoneMessage(Task task) {
        printMessageToUser("Nice! I've marked this task as done: "
                + System.lineSeparator() + "  " + task);
    }

    /**
     * Prints the list of tasks to the user.
     *
     * @param tasks List of tasks the user has.
     */
    public void printTasks(ArrayList<Task> tasks) {
        printDivider();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printDivider();
    }


    /**
     * Prints the list of tasks that contains a keyword in their description.
     * Notifies user if there is no task contains the keyword they are searching for.
     *
     * @param tasks List of tasks containing the keyword in their description.
     */
    public void printFoundTasks(ArrayList<Task> tasks) {
        printDivider();
        if (tasks.size() == 0) {
            System.out.println(NO_MATCHING_TASK_MESSAGE);
        } else {
            System.out.println("Here are the matching tasks in your list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        printDivider();
    }

    /**
     * Prints a message to notify the user that task list data was successfully loaded into the
     * current program instance.
     */
    public void printSuccessfulDataLoading() {
        System.out.println(SUCCESSFUL_DATA_LOAD_MESSAGE);
    }

    /**
     * Prints a message to notify the user that no task list data was detected.
     */
    public void printNoDataLoaded() {
        System.out.println(FAIL_DATA_LOAD_MESSAGE);
    }


    /**
     * Prints an error message, according to the specific error that occurred, to the user.
     *
     * @param errorType Identity of the error that occurred.
     */
    public void printDukeErrorMessage(String errorType) {
        switch (errorType) {
        case ERROR_UNKNOWN_COMMAND:
            printMessageToUser(ERROR_COMMAND_MESSAGE);
            break;
        case ERROR_TODO_EMPTY_DESCRIPTION:
            printMessageToUser(ERROR_TODO_EMPTY_DESCRIPTION_MESSAGE);
            break;
        case ERROR_EVENT_EMPTY_DESCRIPTION:
            printMessageToUser(ERROR_EVENT_EMPTY_DESCRIPTION_MESSAGE);
            break;
        case ERROR_EVENT_WRONG_FORMAT:
            printMessageToUser(ERROR_EVENT_WRONG_FORMAT_MESSAGE);
            break;
        case ERROR_DEADLINE_EMPTY_DESCRIPTION:
            printMessageToUser(ERROR_DEADLINE_EMPTY_DESCRIPTION_MESSAGE);
            break;
        case ERROR_DEADLINE_WRONG_FORMAT:
            printMessageToUser(ERROR_DEADLINE_WRONG_FORMAT_MESSAGE);
            break;
        case ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION:
            printMessageToUser(ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION_MESSAGE);
            break;
        case ERROR_MARKTASKASDONE_WRONG_INDEX:
            printMessageToUser(ERROR_MARKTASKASDONE_WRONG_INDEX_MESSAGE);
            break;
        case ERROR_MARKTASKASDONE_NOT_NUMBER:
            printMessageToUser(ERROR_MARKTASKASDONE_NOT_NUMBER_MESSAGE);
            break;
        case ERROR_DELETE_TASK_EMPTY_DESCRIPTION:
            printMessageToUser(ERROR_DELETE_TASK_EMPTY_DESCRIPTION_MESSAGE);
            break;
        case ERROR_DELETE_TASK_NOT_NUMBER:
            printMessageToUser(ERROR_DELETE_TASK_NOT_NUMBER_MESSAGE);
            break;
        case ERROR_DELETE_TASK_WRONG_INDEX:
            printMessageToUser(ERROR_DELETE_TASK_WRONG_INDEX_MESSAGE);
            break;
        case ERROR_FIND_TASK_EMPTY_DESCRIPTION:
            printMessageToUser(ERROR_FIND_TASK_EMPTY_DESCRIPTION_MESSAGE);
            break;
        case ERROR_UNKNOWN_TASK_TYPE:
            printMessageToUser(ERROR_UNKNOWN_TASK_INDICATOR_MESSAGE);
            break;
        case ERROR_WITH_FILE:
            printMessageToUser(FILE_ERROR_MESSAGE);
            break;
        default:
            printMessageToUser(UNEXPECTED_ERROR);
            break;
        }
    }
}
