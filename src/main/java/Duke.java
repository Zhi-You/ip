import java.util.Scanner;

public class Duke {
    // To allow Duke to handle the commands specified by the user
    public static void handleUserInputs() {
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
            if (command.equals("bye")) {
                break;
            }

            switch (command) {
            case "list":
                displayManager.printList(tasks);
                break;
            case "done":
                // Gets index of the task user specified to be done
                int taskDoneIndex = Integer.parseInt(taskDescription.split(" ")[0]) - 1;

                // Mark task as done
                tasks[taskDoneIndex].markAsDone();

                // Notifies user that the task is marked as done
                displayManager.printMarkAsDoneMessage(tasks[taskDoneIndex]);
                break;
            case "todo":
                addTodoTask(tasks, taskDescription);

                // Notifies user that task has been stored
                displayManager.printTaskAddedMessage(tasks[Task.getTaskCount() - 1]);
                break;
            case "deadline":
                addDeadlineTask(tasks, taskDescription);

                displayManager.printTaskAddedMessage(tasks[Task.getTaskCount() - 1]);
                break;
            case "event":
                addEventTask(tasks, taskDescription);

                displayManager.printTaskAddedMessage(tasks[Task.getTaskCount() - 1]);
                break;
            }
        }
    }

    // Extracts command word from user inputs to decide what Duke will do
    public static String getCommandFromInput(String userInput) {
        // Split user input into different words to distinguish between command and task's information
        String[] inputParts = userInput.split(" ");

        // User's command can be found in the first word of user's inputs
        return inputParts[0].toLowerCase();
    }

    // Extracts task information from user inputs to decide what kind of task to add to list
    private static String getTaskDescriptionFromInput(String userInput) {
        // Split user input into different words to distinguish between command and task's information
        String[] inputParts = userInput.split(" ");

        String taskDescription = "";

        // Get the informative parts from user input after the command (first word)
        for (int i = 1; i < inputParts.length; i++) {
            taskDescription = String.join(" ", taskDescription, inputParts[i]);
        }
        taskDescription = taskDescription.trim();

        return taskDescription;
    }

    // Add ToDos typed task into the tasks array
    private static void addTodoTask(Task[] tasks, String taskDescription) {
        tasks[Task.getTaskCount()] = new Todo(taskDescription);
    }

    // Add Deadlines typed task into the tasks array
    private static void addDeadlineTask(Task[] tasks, String taskDescription) {
        // Split user input to allow for retrieval of task details and deadline
        String[] deadlineTaskParts = taskDescription.split("/by");
        String description = deadlineTaskParts[0];
        String by = deadlineTaskParts[1];

        tasks[Task.getTaskCount()] = new Deadline(description, by);
    }

    // Add Events typed task into the tasks array
    private static void addEventTask(Task[] tasks, String taskDescription) {
        // Split user input to allow for retrieval of task details and event timing
        String[] eventTaskParts = taskDescription.split("/at");
        String description = eventTaskParts[0];
        String at = eventTaskParts[1];

        tasks[Task.getTaskCount()] = new Event(description, at);
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
        handleUserInputs();

        // Prints exit message
        displayManager.printExitMessage();
    }
}
