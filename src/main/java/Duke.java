import java.util.Scanner;

public class Duke {

    // Prints horizontal lines to wrap output for a clearer display
    public static void printHorizontalLine() {
        String horizontalLine = "________________________________________________";
        System.out.println(horizontalLine);
    }

    // To store text (tasks) entered by user and display them back when requested
    public static void handleCommands() {
        // To store tasks specified by user
        String[] tasks = new String[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);

        String command;

        // Gets initial command
        command = in.nextLine();

        // Continuously ask user for commands until user inputs "bye"
        while (true) {
            // Exits loop when user inputs "bye"
            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                // Displays stored tasks to user when requested
                printHorizontalLine();
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                printHorizontalLine();
            } else {
                // Store text into tasks
                tasks[taskCount] = command;
                taskCount++;

                // Notifies user that task has been stored
                printHorizontalLine();
                System.out.println("added: " + command);
                printHorizontalLine();
            }

            // Gets next command from user
            command = in.nextLine();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Prints welcome message
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();

        // Allows user to input commands for adding tasks and displaying them
        handleCommands();

        // Prints exit message
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
