import java.util.Scanner;

public class Duke {
    // To allow Duke to handle the commands specified by the user
    public static void handleCommands() {
        // Array of Task objects to store tasks specified by user
        Task[] tasks = new Task[100];
        int taskCount = 0;

        // Handles formatting of display
        DisplayManager displayManager = new DisplayManager();

        Scanner in = new Scanner(System.in);
        String command;

        // Gets initial command
        command = in.nextLine();

        // Continuously asks user for commands until user inputs "bye"
        while (true) {
            // Exits loop when user inputs "bye"
            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                displayManager.printHorizontalLine();
                // Displays stored tasks to user when requested
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].description);
                }
                displayManager.printHorizontalLine();
            } else if (command.contains("done")) {
                // Gets index of the task user specified to be done
                int taskDoneIndex = Integer.parseInt(command.split(" ")[1]) - 1;

                // Mark task as done
                tasks[taskDoneIndex].markAsDone();

                // Notifies user that the task is marked as done
                displayManager.printMessageToUser("Nice! I've marked this task as done: "
                        + System.lineSeparator() + "  " + tasks[taskDoneIndex].getStatusIcon()
                        + " " + tasks[taskDoneIndex].description);
            } else {
                // Store text into tasks
                tasks[taskCount] = new Task(command);
                taskCount++;

                // Notifies user that task has been stored
                displayManager.printMessageToUser("added: " + command);
            }

            // Gets next command from user
            command = in.nextLine();
        }
    }

    public static void main(String[] args) {
        DisplayManager displayManager = new DisplayManager();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Prints welcome message
        displayManager.printWelcomeMessage();

        // Allows user to get help from Duke
        handleCommands();

        // Prints exit message
        displayManager.printExitMessage();
    }
}
