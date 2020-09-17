package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataManager {
    // Specifying file paths in an OS-independent way
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final Path DATA_DIRECTORY = Paths.get(CURRENT_DIRECTORY, "data");
    private static final Path DATA_PATH = Paths.get(CURRENT_DIRECTORY, "data", "duke.txt");

    // Different parts of a task when it is converted to a String
    private static final int TASK_TYPE_INDICATOR = 1;
    private static final int START_OF_TASK_DESCRIPTION = 7;
    private static final String DEADLINE_TASK_DELIMITER = " \\(by:";
    private static final String EVENT_TASK_DELIMITER = " \\(at:";

    public static void saveTasksData(Task[] tasks) throws IOException {
        ArrayList<String> tasksStringList = new ArrayList<>();

        // Get a string representation of the tasks and store it into an ArrayList
        for (int i = 0; i < Task.getTaskCount(); i++) {
            tasksStringList.add(tasks[i].toString());
        }

        // Write tasks to data file - a text file
        Files.write(DATA_PATH, tasksStringList);
    }

    public static void loadTasksData(Task[] tasks) throws IOException {

        // If folder (data) and file (duke.txt) for saving and loading does not exist yet, create one for user.
        createSaveLocation();

        // Requires casting as it only returns a list - superclass of arrayList
        ArrayList<String> loadedTasksStringList = (ArrayList<String>) Files.readAllLines(DATA_PATH);

        for (String task : loadedTasksStringList) {

            TaskType taskType;

            // Get task type while catching unknown-task-type error
            try {
                taskType = getTaskType(task.charAt(TASK_TYPE_INDICATOR));
            } catch (DukeException e) {
                // Print error message to user and terminate loading of data file if task type is not found
                DisplayManager.printDukeErrorMessage(e.getMessage());
                return;
            }

            // Using enums to check the type of task we are dealing with
            switch(taskType) {
            case TODO:
                processTodoTask(task, tasks);
                break;
            case DEADLINE:
                processDeadlineTask(task, tasks);
                break;
            case EVENT:
                processEventTask(task, tasks);
                break;
            }
        }
    }

    // If folder (data) and file (duke.txt) for saving and loading does not exist yet, create one for user.
    private static void createSaveLocation() throws IOException {
        if (!Files.exists(DATA_DIRECTORY)) {
            Files.createDirectory(DATA_DIRECTORY);
        }
        if (!Files.exists(DATA_PATH)) {
            Files.createFile(DATA_PATH);
        }
    }

    // Identifies the given task type
    private static TaskType getTaskType(char taskTypeIndicator) throws DukeException {

        // No breaks required as return statements are used
        switch(taskTypeIndicator) {
        case 'T':
            return TaskType.TODO;
        case 'D':
            return TaskType.DEADLINE;
        case 'E':
            return TaskType.EVENT;
        default:
            throw new DukeException(ErrorTypeManager.ERROR_UNKNOWN_TASK_INDICATOR);
        }
    }

    // Identify if task was marked as done before and mark it as done in current session if yes
    private static void markTaskDoneIfDone (String task, Task[] tasks) {
        if (task.contains(Task.TICK_SYMBOL)) {
            tasks[Task.getTaskCount() - 1].markAsDone();
        }
    }

    // Exceptions are already handled in Duke.java and hence we will assume these are appropriate tasks
    // No need to notify user that the task has been added too
    private static void processTodoTask(String task, Task[] tasks) {
        String taskDescription = task.substring(START_OF_TASK_DESCRIPTION);

        // Load saved to_do task into current tasks
        tasks[Task.getTaskCount()] = new Todo(taskDescription);

        // Recover its isDone status
        markTaskDoneIfDone(task, tasks);
    }

    // Deadline tasks should be in the right format since exceptions were handled in Duke.java
    // No need to notify user that the task has been added too
    private static void processDeadlineTask(String task, Task[] tasks) {
        String taskDescription = task.substring(START_OF_TASK_DESCRIPTION);

        String[] deadlineTaskParts = taskDescription.split(DEADLINE_TASK_DELIMITER);
        String deadlineTaskDescription = deadlineTaskParts[0];
        // Took the substring of the deadline portion because we want to ignore the closing bracket at the end
        String deadline = deadlineTaskParts[1].substring(0, deadlineTaskParts[1].length() - 1);

        // Load saved deadline task into current tasks
        tasks[Task.getTaskCount()] = new Deadline(deadlineTaskDescription, deadline);

        // Recover its isDone status
        markTaskDoneIfDone(task, tasks);
    }

    // Event tasks should be in the right format since exceptions were handled in Duke.java
    // No need to notify user that the task has been added too
    private static void processEventTask(String task, Task[] tasks) {
        String taskDescription = task.substring(START_OF_TASK_DESCRIPTION);

        String[] eventTaskParts = taskDescription.split(EVENT_TASK_DELIMITER);
        String eventTaskDescription = eventTaskParts[0];
        // Took the substring of the deadline portion because we want to ignore the closing bracket at the end
        String eventTime = eventTaskParts[1].substring(0, eventTaskParts[1].length() - 1);

        // Load saved event task into current tasks
        tasks[Task.getTaskCount()] = new Event(eventTaskDescription, eventTime);

        // Recover its isDone status
        markTaskDoneIfDone(task, tasks);
    }
}
