package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static final int MAX_NUMBER_OF_TASKS = 100;

    // Constants for commands
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK_TASK_DONE = "done";
    private static final String COMMAND_TODO_TASK = "todo";
    private static final String COMMAND_DEADLINE_TASK = "deadline";
    private static final String COMMAND_EVENT_TASK = "event";

    // Constant for user's exit status
    private static final boolean EXIT_COMMAND_IS_PASSED = true;

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DEADLINE_TASK_DELIMITER = "/by";
    private static final String EVENT_TASK_DELIMITER = "/at";

    public static void main(String[] args) {
        // Prints duke.Duke's hello message and logo
        DisplayManager.printDukeHelloMessage();

        // Prints welcome message
        DisplayManager.printWelcomeMessage();

        // Allows user to get help from duke.Duke
        handleUserInputs();

        // Prints exit message
        DisplayManager.printExitMessage();
    }

    // Handles the exception thrown by processUserInputs and repeat execution until user exits duke.Duke
    private static void handleUserInputs() {
        // Array of duke.task.Task objects to store tasks specified by user
        Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];

        // Load tasks data when Duke starts up
        try {
            DataManager.loadTasksData(tasks);
        } catch (IOException e) {
            DisplayManager.printFileErrorMessage();
        }

        boolean exitCommandPassed = false;

        while (!exitCommandPassed) {
            // Catch any exceptions that are thrown when processing user inputs
            try {
                exitCommandPassed = processUserInputs(tasks);
            } catch (DukeException e) {
                DisplayManager.printDukeErrorMessage(e.getMessage());
            } catch (IOException e) {
                DisplayManager.printFileErrorMessage();
            }
        }
    }

    // To allow duke.Duke to get and process the inputs specified by the user
    private static boolean processUserInputs(Task[] tasks) throws DukeException, IOException {
        String userInput;
        String command;
        String taskDescription;

        // Continuously asks user for inputs until user exits
        while (true) {
            userInput = SCANNER.nextLine();
            command = getCommandFromInput(userInput);
            taskDescription = getTaskDescriptionFromInput(userInput);

            // Exits loop when user inputs "bye"
            if (command.equals(COMMAND_EXIT)) {
                return EXIT_COMMAND_IS_PASSED;
            }

            // duke.Duke reads user commands and either show task list, mark task as done or add task
            switch (command) {
            case COMMAND_LIST:
                DisplayManager.printList(tasks);
                break;
            case COMMAND_MARK_TASK_DONE:
                markTaskAsDone(tasks, taskDescription);
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
                throw new DukeException(ErrorTypeManager.ERROR_UNKNOWN_COMMAND);
            }

            // Saves tasks whenever the task list changes
            DataManager.saveTasksData(tasks);
        }
    }

    // Extracts command word from user inputs to decide what duke.Duke will do
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

    // Given an index, able to mark the task at that index in the tasks array to be done
    private static void markTaskAsDone(Task[] tasks, String taskDescription) throws DukeException {
        // Catches the exception whereby user did not input any description after done command
        if (taskDescription.isBlank()) {
            throw new DukeException(ErrorTypeManager.ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION);
        }

        int taskDoneIndex;

        // Gets index of the task user specified to be done
        // Catch exceptions whereby if index specified is not in numeric form
        try {
            taskDoneIndex = Integer.parseInt(taskDescription.split(" ")[0]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ErrorTypeManager.ERROR_MARKTASKASDONE_NOT_NUMBER);
        }

        // Marks specified task as done
        // Catch exceptions whereby user specified a negative index or an index without a task stored yet
        try {
            tasks[taskDoneIndex].markAsDone();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(ErrorTypeManager.ERROR_MARKTASKASDONE_WRONG_INDEX);
        }

        // Notifies user that the task is marked as done
        DisplayManager.printMarkAsDoneMessage(tasks[taskDoneIndex]);
    }


    // Adds ToDos typed task into the tasks array
    private static void addTodoTask(Task[] tasks, String taskDescription) throws DukeException {
        // Catches the exception whereby user did not input any description after to_do command
        if (taskDescription.isBlank()) {
            throw new DukeException(ErrorTypeManager.ERROR_TODO_EMPTY_DESCRIPTION);
        }

        tasks[Task.getTaskCount()] = new Todo(taskDescription);

        // Notifies user that task has been added
        DisplayManager.printTaskAddedMessage(tasks[Task.getTaskCount() - 1]);
    }


    // Adds duke.task.Deadline typed task into the tasks array
    private static void addDeadlineTask(Task[] tasks, String taskDescription) throws DukeException {
        // Catches the exception whereby user did not input any description after deadline command
        if (taskDescription.isBlank()) {
            throw new DukeException(ErrorTypeManager.ERROR_DEADLINE_EMPTY_DESCRIPTION);
        }

        // Splits user input into task details and deadline
        String[] deadlineTaskParts = taskDescription.split(DEADLINE_TASK_DELIMITER);
        String deadlineTaskDescription;
        String deadline;
        // Catches exceptions where user does not specify deadline in the correct format
        // Also catches specific input of: "deadline /by " where both description and deadline are empty
        try {
            deadlineTaskDescription = deadlineTaskParts[0];
            deadline = deadlineTaskParts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ErrorTypeManager.ERROR_DEADLINE_WRONG_FORMAT);
        }

        // Catches the exception whereby user did not input any task description but has a deadline
        // Sample input that will get caught: "deadline /by tmr"
        if (deadlineTaskDescription.isBlank()) {
            throw new DukeException(ErrorTypeManager.ERROR_DEADLINE_EMPTY_DESCRIPTION);
        }

        tasks[Task.getTaskCount()] = new Deadline(deadlineTaskDescription, deadline);

        // Notifies user that task has been added
        DisplayManager.printTaskAddedMessage(tasks[Task.getTaskCount() - 1]);
    }


    // Adds duke.task.Event typed task into the tasks array
    private static void addEventTask(Task[] tasks, String taskDescription) throws DukeException {
        // Catches the exception whereby user did not input any description after event command
        if (taskDescription.isBlank()) {
            throw new DukeException(ErrorTypeManager.ERROR_EVENT_EMPTY_DESCRIPTION);
        }

        // Splits user input into task details and event timing
        String[] eventTaskParts = taskDescription.split(EVENT_TASK_DELIMITER);
        String eventTaskDescription;
        String eventTime;
        // Catches exceptions where user does not specify event in the correct format
        // Also catches specific input of: "event /at " where both description and event time are empty
        try {
            eventTaskDescription = eventTaskParts[0];
            eventTime = eventTaskParts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ErrorTypeManager.ERROR_EVENT_WRONG_FORMAT);
        }

        // Catches the exception whereby user did not input any task description but has an event time
        // Sample input that will get caught: "event /at tmr"
        if (eventTaskDescription.isBlank()) {
            throw new DukeException(ErrorTypeManager.ERROR_EVENT_EMPTY_DESCRIPTION);
        }

        tasks[Task.getTaskCount()] = new Event(eventTaskDescription, eventTime);

        // Notifies user that task has been added
        DisplayManager.printTaskAddedMessage(tasks[Task.getTaskCount() - 1]);
    }
}
