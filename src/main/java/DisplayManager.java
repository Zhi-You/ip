public class DisplayManager {

    private static final String DIVIDER = "________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke" + System.lineSeparator()
            + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

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
}
