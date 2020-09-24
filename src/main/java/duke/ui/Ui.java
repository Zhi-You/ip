package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.exception.ErrorMessages.ERROR_COMMAND_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_DEADLINE_EMPTY_DESCRIPTION_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_DEADLINE_WRONG_FORMAT_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_DELETETASK_EMPTY_DESCRIPTION_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_DELETETASK_NOT_NUMBER_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_DELETETASK_WRONG_INDEX_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_EVENT_EMPTY_DESCRIPTION_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_EVENT_WRONG_FORMAT_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_MARKTASKASDONE_NOT_NUMBER_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_MARKTASKASDONE_WRONG_INDEX_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_TODO_EMPTY_DESCRIPTION_MESSAGE;
import static duke.exception.ErrorMessages.ERROR_UNKNOWN_TASK_INDICATOR_MESSAGE;
import static duke.exception.ErrorMessages.FILE_ERROR_MESSAGE;
import static duke.exception.ErrorMessages.UNEXPECTED_ERROR;

import static duke.exception.ErrorTypeManager.ERROR_DEADLINE_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_DEADLINE_WRONG_FORMAT;
import static duke.exception.ErrorTypeManager.ERROR_DELETETASK_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_DELETETASK_NOT_NUMBER;
import static duke.exception.ErrorTypeManager.ERROR_DELETETASK_WRONG_INDEX;
import static duke.exception.ErrorTypeManager.ERROR_EVENT_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_EVENT_WRONG_FORMAT;
import static duke.exception.ErrorTypeManager.ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_MARKTASKASDONE_NOT_NUMBER;
import static duke.exception.ErrorTypeManager.ERROR_MARKTASKASDONE_WRONG_INDEX;
import static duke.exception.ErrorTypeManager.ERROR_TODO_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_UNKNOWN_COMMAND;
import static duke.exception.ErrorTypeManager.ERROR_UNKNOWN_TASK_INDICATOR;
import static duke.exception.ErrorTypeManager.ERROR_WITH_FILE;

public class Ui {
    private final String DIVIDER = "______________________________________________________";
    private final String WELCOME_MESSAGE = "Hello! I'm Duke" + System.lineSeparator()
            + "What can I do for you?";
    private final String NOTE_TO_USER = "(Available commands are: 'list', 'todo', 'event',"
            + System.lineSeparator() + "'deadline', 'done', 'delete' and 'bye')"
            + System.lineSeparator() + "(Note: Dates should be given in 'yyyy-mm-dd' format)";

    private final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";


    // file load status
    private final String SUCCESSFUL_DATA_LOAD_MESSAGE = "(Note: "
            + "Your saved data was successfully loaded!)";
    private final String FAIL_DATA_LOAD_MESSAGE = "(Note: "
            + "Could not detect any saved data!)";


    private final Scanner SCANNER = new Scanner(System.in);


    public String getUserInput() {
        return SCANNER.nextLine();
    }

    // Prints horizontal lines to wrap output for a clearer display
    public void printDivider() {
        System.out.println(DIVIDER);
    }

    // Prints message to user wrapped with horizontal lines for clear display
    public void printMessageToUser(String message) {
        printDivider();
        System.out.println(message);
        printDivider();
    }

    private void printDukeHelloMessage() {
        System.out.println("Hello from\n" + DUKE_LOGO);
    }

    private void printNoteToUser() {
        System.out.println(NOTE_TO_USER);
    }

    // Prints welcome message to user
    public void printWelcomeMessage() {
        printDukeHelloMessage();
        printDivider();
        printNoteToUser();
        printMessageToUser(WELCOME_MESSAGE);
    }

    // Prints exit message to user
    public void printExitMessage() {
        printMessageToUser(EXIT_MESSAGE);
    }

    // Prints message to indicate user has added a task
    public void printTaskAddedMessage(Task task, int numberOfTasks) {
        printMessageToUser("Got it. I've added this task:" + System.lineSeparator()
                + "  " + task + System.lineSeparator()
                + printTaskCount(numberOfTasks));
    }

    // Prints message to indicate user has deleted a task
    public void printTaskDeletedMessage (Task task, int numberOfTasks) {
        printMessageToUser("Noted. I've removed this task: " + System.lineSeparator()
                + "  " + task + System.lineSeparator()
                + printTaskCount(numberOfTasks - 1));
    }

    // Prints the current count of the tasks in the task ArrayList
    public String printTaskCount(int numberOfTasks) {
        return("Now you have " + numberOfTasks +
                (numberOfTasks == 1 ? " task in the list." : " tasks in the list."));
    }

    // Prints message to indicate user has marked a task as done
    public void printMarkAsDoneMessage(Task task) {
        printMessageToUser("Nice! I've marked this task as done: "
                + System.lineSeparator() + "  " + task);
    }


    // Prints list out for user when list command is received.
    public void printTasks(ArrayList<Task> tasks) {
        printDivider();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printDivider();
    }

    public void printSuccessfulDataLoading() {
        System.out.println(SUCCESSFUL_DATA_LOAD_MESSAGE);
    }

    public void printNoDataLoaded() {
        System.out.println(FAIL_DATA_LOAD_MESSAGE);
    }

    // Prints different error messages for different exceptions encountered
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
        case ERROR_DELETETASK_EMPTY_DESCRIPTION:
            printMessageToUser(ERROR_DELETETASK_EMPTY_DESCRIPTION_MESSAGE);
            break;
        case ERROR_DELETETASK_NOT_NUMBER:
            printMessageToUser(ERROR_DELETETASK_NOT_NUMBER_MESSAGE);
            break;
        case ERROR_DELETETASK_WRONG_INDEX:
            printMessageToUser(ERROR_DELETETASK_WRONG_INDEX_MESSAGE);
            break;
        case ERROR_UNKNOWN_TASK_INDICATOR:
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
