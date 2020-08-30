public class DisplayManager {

    public DisplayManager() {
    }

    private String horizontalLine = "________________________________________________";

    // Prints horizontal lines to wrap output for a clearer display
    public void printHorizontalLine() {
        System.out.println(horizontalLine);
    }

    // Prints message to user wrapped with horizontal lines for clear display
    public void printMessageToUser(String message) {
        printHorizontalLine();
        System.out.println(message);
        printHorizontalLine();
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
}
