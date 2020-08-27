import java.util.Scanner;

public class Duke {

    // Prints horizontal lines to wrap output for a clearer display
    public static void printHorizontalLine() {
        String horizontalLine = "________________________________________________";
        System.out.println(horizontalLine);
    }

    // To allow Duke to handle the commands specified by the user
    public static void handleCommands() {
        // Array of Task objects to store tasks specified by user
        Task[] tasks = new Task[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);

        String command;

        // Gets initial command
        command = in.nextLine();
        printHorizontalLine();

        // Continuously asks user for commands until user inputs "bye"
        while (true) {
            // Exits loop when user inputs "bye"
            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                // Displays stored tasks to user when requested
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].description);
                }
                printHorizontalLine();
            } else if (command.contains("done")) {
                // Gets index of the task user specified to be done
                int taskDoneIndex = Integer.parseInt(command.split(" ")[1]) - 1;

                // Mark task as done
                tasks[taskDoneIndex].markAsDone();

                System.out.println("Nice! I've marked this task as done: \n  " + tasks[taskDoneIndex].getStatusIcon() + " " + tasks[taskDoneIndex].description);
                printHorizontalLine();
            } else {
                // Store text into tasks
                tasks[taskCount] = new Task(command);
                taskCount++;

                // Notifies user that task has been stored
                System.out.println("added: " + command);
                printHorizontalLine();
            }

            // Gets next command from user
            command = in.nextLine();
            printHorizontalLine();
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

        // Allows user to get help from Duke
        handleCommands();

        // Prints exit message
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
