import java.util.Scanner;

public class Duke {
    // To allow Duke to handle the commands specified by the user
    public static void handleCommands() {
        // Array of Task objects to store tasks specified by user
        Task[] tasks = new Task[100];

        // Handles formatting of display
        DisplayManager displayManager = new DisplayManager();

        Scanner in = new Scanner(System.in);
        String userInput;
        String command;
        String taskDescription;


        // Continuously asks user for inputs until user exits
        while (true) {
            userInput = in.nextLine();
            command = getCommandFromInput(userInput);
            taskDescription = getTaskDescriptionFromInput(userInput);

            // Exits loop when user inputs "bye"
            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                displayManager.printList(tasks);
            } else if (command.equalsIgnoreCase("done")) {
                // Gets index of the task user specified to be done
                int taskDoneIndex = Integer.parseInt(taskDescription.split(" ")[0]) - 1;

                // Mark task as done
                tasks[taskDoneIndex].markAsDone();

                // Notifies user that the task is marked as done
                displayManager.printMessageToUser("Nice! I've marked this task as done: "
                        + System.lineSeparator() + "  " + tasks[taskDoneIndex].getStatusIcon()
                        + " " + tasks[taskDoneIndex].getDescription());
            } else {
                // Store text into tasks
                tasks[Task.getTaskCount()] = new Task(taskDescription);

                // Notifies user that task has been stored
                displayManager.printMessageToUser("added: " + taskDescription);
            }
        }
    }


    public static String getCommandFromInput(String userInput) {
        String[] inputParts = userInput.split(" ");

        // User's command can be found in the first word of user's inputs
        String command = inputParts[0];

        return command;
    }

    private static String getTaskDescriptionFromInput(String userInput) {
        String[] inputParts = userInput.split(" ");

        String taskDescription = "";

        // Get the relevant parts from user input after the command (first word)
        for (int i = 1; i < inputParts.length; i++) {
            taskDescription = String.join(" ", taskDescription, inputParts[i]);
        }
        taskDescription = taskDescription.trim();
        System.out.println(taskDescription);
        return taskDescription;
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
