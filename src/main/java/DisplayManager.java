public class DisplayManager {

    public DisplayManager() {
    }

    private final String DIVIDER = "________________________________________________";

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

    public void printTaskAddedMessage(Task task) {
        printMessageToUser("Got it. I've added this task:" + System.lineSeparator()
                + "  " + task + System.lineSeparator()
                + "Now you have " + Task.getTaskCount()
                + (Task.getTaskCount() == 1? " task in the list." : " tasks in the list."));
    }

    public void printMarkAsDoneMessage(Task task) {
        printMessageToUser("Nice! I've marked this task as done: "
                + System.lineSeparator() + "  " + task);
    }

    String welcomeMessage = "Hello! I'm Duke" + System.lineSeparator()
            + "What can I do for you?";


    public void printWelcomeMessage() {
        printMessageToUser(welcomeMessage);
    }

    String exitMessage = "Bye. Hope to see you again soon!";

    public void printExitMessage() {
        printMessageToUser(exitMessage);
    }

    // Prints list out for user when list command is received.
    public void printList(Task[] tasks) {
        printDivider();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        printDivider();
    }
}
