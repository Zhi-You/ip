package duke.task;

import duke.ui.Ui;
import duke.exception.DukeException;

import java.util.ArrayList;

import static duke.exception.ErrorTypeManager.ERROR_DEADLINE_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_DEADLINE_WRONG_FORMAT;
import static duke.exception.ErrorTypeManager.ERROR_DELETETASK_NOT_NUMBER;
import static duke.exception.ErrorTypeManager.ERROR_DELETETASK_WRONG_INDEX;
import static duke.exception.ErrorTypeManager.ERROR_EVENT_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_EVENT_WRONG_FORMAT;
import static duke.exception.ErrorTypeManager.ERROR_MARKTASKASDONE_NOT_NUMBER;
import static duke.exception.ErrorTypeManager.ERROR_MARKTASKASDONE_WRONG_INDEX;
import static duke.exception.ErrorTypeManager.ERROR_NO_DATA_TO_LOAD;

/**
 * Represents the task list and contains methods to operate on tasks.
 */
public class TaskList {
    private final String DEADLINE_TASK_DELIMITER = "/by";
    private final String EVENT_TASK_DELIMITER = "/at";

    private ArrayList<Task> tasks;

    private final Ui ui;

    /**
     * Constructs task list from stored data (if any).
     * Also creates a new Ui object and prints a success message if data was loaded successfully.
     *
     * @param loadedTasks List of tasks loaded from save file.
     * @throws DukeException If there is no loaded data.
     */
    public TaskList(ArrayList<Task> loadedTasks) throws DukeException {
        if (loadedTasks.size() == 0) {
            throw new DukeException(ERROR_NO_DATA_TO_LOAD);
        }

        tasks = loadedTasks;
        ui = new Ui();
        ui.printSuccessfulDataLoading();
    }

    /**
     * Constructs an empty task list if there is no data to be loaded.
     * Creates a new Ui object as well.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }

    /**
     * Returns a list of tasks that the user has currently.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the current count for the number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Prints the list of tasks to the user.
     */
    public void printTaskList() {
        ui.printTasks(tasks);
    }

    /**
     * Marks a task as done in the task list based on the index of the task passed in as
     * the task description for the done command. If the operation is carried out successfully,
     * a message will be printed out to notify the user that the specific task is marked as done.
     *
     * @param taskDescription Description of the task to be marked as done in the form of its index.
     * @throws DukeException If the task description is not a number to parse,
     * or if the index passed in does not correspond to any task in the task list.
     */
    public void markTaskAsDone(String taskDescription) throws DukeException {
        int completedTaskIndex;
        Task taskToBeMarkedAsDone;

        try {
            completedTaskIndex = Integer.parseInt(taskDescription.split(" ")[0]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_MARKTASKASDONE_NOT_NUMBER);
        }

        try {
            taskToBeMarkedAsDone = tasks.get(completedTaskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_MARKTASKASDONE_WRONG_INDEX);
        }

        taskToBeMarkedAsDone.markAsDone();

        ui.printMarkAsDoneMessage(taskToBeMarkedAsDone);
    }

    /**
     * Deletes a task in the task list based on the index of the task passed in as
     * the task description for the delete command. If the operation is carried out successfully,
     * a message will be printed out to notify the user that the specific task is deleted.
     *
     * @param taskDescription Description of the task to be deleted in the form of its index.
     * @throws DukeException If the task description is not a number to parse,
     * or if the index passed in does not correspond to any task in the task list.
     */
    public void deleteTask(String taskDescription) throws DukeException {
        int taskToDeleteIndex;
        Task taskToBeDeleted;

        try {
            taskToDeleteIndex = Integer.parseInt(taskDescription.split(" ")[0]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_DELETETASK_NOT_NUMBER);
        }

        try {
            taskToBeDeleted = tasks.get(taskToDeleteIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_DELETETASK_WRONG_INDEX);
        }

        ui.printTaskDeletedMessage(taskToBeDeleted, getTaskCount());
        tasks.remove(taskToBeDeleted);
    }


    /**
     * Adds a Todo task, with its description filled, into the task list.
     * Prints a message to notify the user if the Todo task is added successfully.
     *
     * @param taskDescription Description of the todo task to be added.
     */
    public void addTodoTask(String taskDescription) {
        Todo newTodoTask = new Todo(taskDescription);
        tasks.add(newTodoTask);

        ui.printTaskAddedMessage(newTodoTask, getTaskCount());
    }


    /**
     * Adds a Deadline task, with its description and deadline filled, into the task list.
     * Prints a message to notify the user if the Deadline task is added successfully.
     *
     * @param taskDescription Description of the Deadline task and its deadline.
     * @throws DukeException If the Deadline task description is given in a wrong format or if it is empty.
     */
    public void addDeadlineTask(String taskDescription) throws DukeException {
        // Splits full task description into task details and deadline
        String[] taskDescriptionParts = taskDescription.split(DEADLINE_TASK_DELIMITER);
        String deadlineTaskDescription;
        String deadline;

        try {
            deadlineTaskDescription = taskDescriptionParts[0];
            deadline = taskDescriptionParts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ERROR_DEADLINE_WRONG_FORMAT);
        }

        if (deadlineTaskDescription.isBlank()) {
            throw new DukeException(ERROR_DEADLINE_EMPTY_DESCRIPTION);
        }

        Deadline newDeadlineTask = new Deadline(deadlineTaskDescription, deadline);
        tasks.add(newDeadlineTask);

        ui.printTaskAddedMessage(newDeadlineTask, getTaskCount());
    }


    /**
     * Adds an Event task, with its description and event time filled, into the task list.
     * Prints a message to notify the user if the Event task is added successfully.
     *
     * @param taskDescription Description of the Event task and its event time.
     * @throws DukeException If the Event task description is given in a wrong format or if it is empty.
     */
    public void addEventTask(String taskDescription) throws DukeException {
        // Splits full task description into task details and event timing
        String[] taskDescriptionParts = taskDescription.split(EVENT_TASK_DELIMITER);
        String eventTaskDescription;
        String eventTime;

        try {
            eventTaskDescription = taskDescriptionParts[0];
            eventTime = taskDescriptionParts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ERROR_EVENT_WRONG_FORMAT);
        }

        if (eventTaskDescription.isBlank()) {
            throw new DukeException(ERROR_EVENT_EMPTY_DESCRIPTION);
        }

        Event newEventTask = new Event(eventTaskDescription, eventTime);
        tasks.add(newEventTask);

        ui.printTaskAddedMessage(newEventTask, getTaskCount());
    }
}
