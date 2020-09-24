package duke.task;

import duke.ui.Ui;
import duke.exception.DukeException;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

import static duke.exception.ErrorTypeManager.ERROR_DEADLINE_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_DEADLINE_WRONG_FORMAT;
import static duke.exception.ErrorTypeManager.ERROR_DELETE_TASK_NOT_NUMBER;
import static duke.exception.ErrorTypeManager.ERROR_DELETE_TASK_WRONG_INDEX;
import static duke.exception.ErrorTypeManager.ERROR_EVENT_EMPTY_DESCRIPTION;
import static duke.exception.ErrorTypeManager.ERROR_EVENT_WRONG_FORMAT;
import static duke.exception.ErrorTypeManager.ERROR_MARKTASKASDONE_NOT_NUMBER;
import static duke.exception.ErrorTypeManager.ERROR_MARKTASKASDONE_WRONG_INDEX;
import static duke.exception.ErrorTypeManager.ERROR_NO_DATA_TO_LOAD;


/**
 * Represents the entire list of tasks entered by the user during runtime and from
 * the data loaded from previous instances of program usage.
 */
public class TaskList {

    private final String DEADLINE_TASK_DELIMITER = "/by";
    private final String EVENT_TASK_DELIMITER = "/at";

    private ArrayList<Task> tasks;

    private final Ui ui;

    /** Constructs task list from stored data */
    public TaskList(ArrayList<Task> loadedTasks) throws DukeException {
        if (loadedTasks.size() == 0) {
            throw new DukeException(ERROR_NO_DATA_TO_LOAD);
        }

        tasks = loadedTasks;
        ui = new Ui();
        ui.printSuccessfulDataLoading();
    }

    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }


    public void printTaskList() {
        ui.printTasks(tasks);
    }


    // Given an index, able to mark the task at that index in the tasks arraylist to be done
    public void markTaskAsDone(String taskIndexDescription) throws DukeException {
        int completedTaskIndex;
        Task taskToBeMarkedAsDone;

        // Gets index of the task user specified to be done
        // Catch exceptions whereby if index specified is not in numeric form
        try {
            completedTaskIndex = Integer.parseInt(taskIndexDescription.split(" ")[0]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_MARKTASKASDONE_NOT_NUMBER);
        }

        // Marks specified task as done
        // Catch exceptions whereby user specified a negative index or an index without a task stored yet
        try {
            taskToBeMarkedAsDone = tasks.get(completedTaskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_MARKTASKASDONE_WRONG_INDEX);
        }

        taskToBeMarkedAsDone.markAsDone();

        // Notifies user that the task is marked as done
        ui.printMarkAsDoneMessage(taskToBeMarkedAsDone);
    }

    // Given an index, able to delete the task at that index in the tasks arraylist
    public void deleteTask(String taskIndexDescription) throws DukeException {
        int taskToDeleteIndex;
        Task taskToBeDeleted;

        // Gets index of the task user specified to be deleted
        // Catch exceptions whereby if index specified is not in numeric form
        try {
            taskToDeleteIndex = Integer.parseInt(taskIndexDescription.split(" ")[0]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_DELETE_TASK_NOT_NUMBER);
        }

        // Delete specified task
        // Catch exceptions whereby user specified a negative index or an index without a task stored yet
        try {
            // Notifies user that the task is deleted.
            // Prints message using the task instance before it is deleted.
            taskToBeDeleted = tasks.get(taskToDeleteIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_DELETE_TASK_WRONG_INDEX);
        }

        ui.printTaskDeletedMessage(taskToBeDeleted, getTaskCount());
        tasks.remove(taskToBeDeleted);
    }

    // Adds ToDos typed task into the tasks arraylist
    public void addTodoTask(String taskDescription) {
        Todo newTodoTask = new Todo(taskDescription);
        tasks.add(newTodoTask);

        // Notifies user that task has been added
        ui.printTaskAddedMessage(newTodoTask, getTaskCount());
    }


    // Adds Deadline typed task into the tasks arraylist
    public void addDeadlineTask(String taskDescription) throws DukeException {
        // Splits user input into task details and deadline
        String[] taskDescriptionParts = taskDescription.split(DEADLINE_TASK_DELIMITER);
        String deadlineTaskDescription;
        String deadline;
        // Catches exceptions where user does not specify deadline in the correct format
        // Also catches specific input of: "deadline /by " where both description and deadline are empty
        try {
            deadlineTaskDescription = taskDescriptionParts[0];
            deadline = taskDescriptionParts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ERROR_DEADLINE_WRONG_FORMAT);
        }

        // Catches the exception whereby user did not input any task description but has a deadline
        // Sample input that will get caught: "deadline /by tmr"
        if (deadlineTaskDescription.isBlank()) {
            throw new DukeException(ERROR_DEADLINE_EMPTY_DESCRIPTION);
        }

        Deadline newDeadlineTask = new Deadline(deadlineTaskDescription, deadline);
        tasks.add(newDeadlineTask);

        // Notifies user that task has been added
        ui.printTaskAddedMessage(newDeadlineTask, getTaskCount());
    }


    // Adds Event typed task into the tasks arraylist
    public void addEventTask(String taskDescription) throws DukeException {
        // Splits user input into task details and event timing
        String[] taskDescriptionParts = taskDescription.split(EVENT_TASK_DELIMITER);
        String eventTaskDescription;
        String eventTime;

        // Catches exceptions where user does not specify event in the correct format
        // Also catches specific input of: "event /at " where both description and event time are empty
        try {
            eventTaskDescription = taskDescriptionParts[0];
            eventTime = taskDescriptionParts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ERROR_EVENT_WRONG_FORMAT);
        }

        // Catches the exception whereby user did not input any task description but has an event time
        // Sample input that will get caught: "event /at tmr"
        if (eventTaskDescription.isBlank()) {
            throw new DukeException(ERROR_EVENT_EMPTY_DESCRIPTION);
        }

        Event newEventTask = new Event(eventTaskDescription, eventTime);
        tasks.add(newEventTask);

        // Notifies user that task has been added
        ui.printTaskAddedMessage(newEventTask, getTaskCount());
    }

    // Given an index, able to mark the task at that index in the tasks arraylist to be done
    public void findTasksAndPrint(String keyword) {
        ArrayList<Task> foundTaskList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(keyword))
                .collect(toList());

        ui.printFoundTasks(foundTaskList);
    }
}
