import java.util.Scanner;

public class Duke {

    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK_TASK_DONE = "done";
    private static final String COMMAND_TODO_TASK = "todo";
    private static final String COMMAND_DEADLINE_TASK = "deadline";
    private static final String COMMAND_EVENT_TASK = "event";

    public static void main(String[] args) {
        // Prints Duke's hello message and logo
        DisplayManager.printDukeHelloMessage();

        // Prints welcome message
        DisplayManager.printWelcomeMessage();

        // Allows user to get help from Duke
        handleUserInputs();

        // Prints exit message
        DisplayManager.printExitMessage();
    }

    // To allow Duke to handle the inputs specified by the user
    private static void handleUserInputs() {
        // Array of Task objects to store tasks specified by user
        Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];

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
            if (command.equals(COMMAND_EXIT)) {
                break;
            }

            // Duke reads user commands and either show task list, mark task as done or add task
            switch (command) {
            case COMMAND_LIST:
                DisplayManager.printList(tasks);
                break;
            case COMMAND_MARK_TASK_DONE:
                markTaskDone(tasks, taskDescription);
                break;
            case COMMAND_TODO_TASK:
                addTodoTask(tasks, taskDescription);
                break;
            case COMMAND_DEADLINE_TASK:
                addDeadlineTask(tasks, taskDescription);
                break;
            case COMMAND_EVENT_TASK:
                addEventTask(tasks, taskDescription);
                break;
            default:
                DisplayManager.printMessageToUser("Please enter the correct command");
                break;
            }
        }
    }

    private static void markTaskDone(Task[] tasks, String taskDescription) {
        // Gets index of the task user specified to be done
        int taskDoneIndex = Integer.parseInt(taskDescription.split(" ")[0]) - 1;

        // Marks task as done
        tasks[taskDoneIndex].markAsDone();

        // Notifies user that the task is marked as done
        DisplayManager.printMarkAsDoneMessage(tasks[taskDoneIndex]);
    }

    // Extracts command word from user inputs to decide what Duke will do
    private static String getCommandFromInput(String userInput) {
        // Splits user input into different words to distinguish between command and task's information
        String[] inputParts = userInput.split(" ");

        // User's command can be found in the first word of user's inputs
        return inputParts[0].toLowerCase();
    }

    // Extracts task information from user inputs to decide what kind of task to add to list
    private static String getTaskDescriptionFromInput(String userInput) {
        // Splits user input into different words to distinguish between command and task's information
        String[] inputParts = userInput.split(" ");

        String taskDescription = "";

        // Gets the informative parts from user input after the command (first word)
        for (int i = 1; i < inputParts.length; i++) {
            taskDescription = String.join(" ", taskDescription, inputParts[i]);
        }
        taskDescription = taskDescription.trim();

        return taskDescription;
    }

    // Adds ToDos typed task into the tasks array
    private static void addTodoTask(Task[] tasks, String taskDescription) {
        tasks[Task.getTaskCount()] = new Todo(taskDescription);

        // Notifies user that task has been added
        DisplayManager.printTaskAddedMessage(tasks[Task.getTaskCount() - 1]);
    }

    // Adds Deadlines typed task into the tasks array
    private static void addDeadlineTask(Task[] tasks, String taskDescription) {
        // Splits user input to allow for retrieval of task details and deadline
        String[] deadlineTaskParts = taskDescription.split("/by");
        String description = deadlineTaskParts[0];
        String by = deadlineTaskParts[1];

        tasks[Task.getTaskCount()] = new Deadline(description, by);

        // Notifies user that task has been added
        DisplayManager.printTaskAddedMessage(tasks[Task.getTaskCount() - 1]);
    }

    // Adds Events typed task into the tasks array
    private static void addEventTask(Task[] tasks, String taskDescription) {
        // Splits user input to allow for retrieval of task details and event timing
        String[] eventTaskParts = taskDescription.split("/at");
        String description = eventTaskParts[0];
        String at = eventTaskParts[1];

        tasks[Task.getTaskCount()] = new Event(description, at);

        // Notifies user that task has been added
        DisplayManager.printTaskAddedMessage(tasks[Task.getTaskCount() - 1]);
    }
}
