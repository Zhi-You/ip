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

    // Error messages
    private static final String SAD_FACE_EMOJI = "\u2639";
    private static final String ERROR_COMMAND_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_TODO_EMPTY_DESCRIPTION_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The description of a todo cannot be empty.";
    private static final String ERROR_EVENT_EMPTY_DESCRIPTION_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The description of an event cannot be empty.";
    private static final String ERROR_DEADLINE_EMPTY_DESCRIPTION_MESSAGE = SAD_FACE_EMOJI
            + " OOPS!!! The description of a deadline cannot be empty.";




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

    // Prints Duke's hello message along with his logo
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
    public static void printErrorMessage(String errorType) {
        System.out.println("ERROR TYPE IS : " + errorType);
        switch (errorType) {
        case ErrorTypeManager.ERROR_UNKNOWN_COMMAND:
            printMessageToUser(ERROR_COMMAND_MESSAGE);
        case ErrorTypeManager.ERROR_TODO_EMPTY_DESCRIPTION:
            printMessageToUser(ERROR_TODO_EMPTY_DESCRIPTION_MESSAGE);
        }
    }

}
