package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    // Constants for commands
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK_TASK_DONE = "done";
    private static final String COMMAND_DELETE_TASK = "delete";
    private static final String COMMAND_TODO_TASK = "todo";
    private static final String COMMAND_DEADLINE_TASK = "deadline";
    private static final String COMMAND_EVENT_TASK = "event";

    // Constant for user's exit status
    private static final boolean EXIT_COMMAND_IS_PASSED = true;

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DEADLINE_TASK_DELIMITER = "/by";
    private static final String EVENT_TASK_DELIMITER = "/at";

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

    // Continually handles the inputs user gives to Duke
    // Handles the exception thrown by processUserInputs and repeat execution until user exits Duke
    private static void handleUserInputs() {
        // ArrayList of Task objects to dynamically store tasks specified by user
        ArrayList<Task> tasks = new ArrayList<>();

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


    // To allow Duke to get and process the inputs specified by the user
    private static boolean processUserInputs(ArrayList<Task> tasks) throws DukeException, IOException {
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

            // Duke reads user commands and either show task list, mark task as done or add task
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
            case COMMAND_DELETE_TASK:
                deleteTask(tasks, taskDescription);
                break;
            default:
                throw new DukeException(ErrorTypeManager.ERROR_UNKNOWN_COMMAND);
            }

            // Saves tasks whenever the task list changes
            DataManager.saveTasksData(tasks);
        }
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

    // Given an index, able to mark the task at that index in the tasks arraylist to be done
    private static void markTaskAsDone(ArrayList<Task> tasks, String taskDescription) throws DukeException {
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
            tasks.get(taskDoneIndex).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ErrorTypeManager.ERROR_MARKTASKASDONE_WRONG_INDEX);
        }

        // Notifies user that the task is marked as done
        DisplayManager.printMarkAsDoneMessage(tasks.get(taskDoneIndex));
    }

    // Given an index, able to delete the task at that index in the tasks arraylist
    private static void deleteTask(ArrayList<Task> tasks, String taskDescription) throws DukeException {
        // Catches the exception whereby user did not input any description after delete command
        if (taskDescription.isBlank()) {
            throw new DukeException(ErrorTypeManager.ERROR_DELETETASK_EMPTY_DESCRIPTION);
        }

        int taskToDeleteIndex;

        // Gets index of the task user specified to be deleted
        // Catch exceptions whereby if index specified is not in numeric form
        try {
            taskToDeleteIndex = Integer.parseInt(taskDescription.split(" ")[0]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ErrorTypeManager.ERROR_DELETETASK_NOT_NUMBER);
        }

        // Delete specified task
        // Catch exceptions whereby user specified a negative index or an index without a task stored yet
        try {
            // Notifies user that the task is deleted.
            // Prints message using the task instance before it is deleted.
            DisplayManager.printTaskDeletedMessage(tasks.get(taskToDeleteIndex));
            tasks.remove(taskToDeleteIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ErrorTypeManager.ERROR_DELETETASK_WRONG_INDEX);
        }

        // Updates current count of tasks in Task ArrayList
        Task.decrementTaskCount();
    }


    // Adds ToDos typed task into the tasks arraylist
    private static void addTodoTask(ArrayList<Task> tasks, String taskDescription) throws DukeException {
        // Catches the exception whereby user did not input any description after to_do command
        if (taskDescription.isBlank()) {
            throw new DukeException(ErrorTypeManager.ERROR_TODO_EMPTY_DESCRIPTION);
        }

        tasks.add(new Todo(taskDescription));

        // Notifies user that task has been added
        DisplayManager.printTaskAddedMessage(tasks.get(Task.getTaskCount() - 1));
    }


    // Adds Deadline typed task into the tasks arraylist
    private static void addDeadlineTask(ArrayList<Task> tasks, String taskDescription) throws DukeException {
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

        tasks.add(new Deadline(deadlineTaskDescription, deadline));

        // Notifies user that task has been added
        DisplayManager.printTaskAddedMessage(tasks.get(Task.getTaskCount() - 1));
    }


    // Adds Event typed task into the tasks arraylist
    private static void addEventTask(ArrayList<Task> tasks, String taskDescription) throws DukeException {
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

        tasks.add(new Event(eventTaskDescription, eventTime));

        // Notifies user that task has been added
        DisplayManager.printTaskAddedMessage(tasks.get(Task.getTaskCount() - 1));
    }
}
