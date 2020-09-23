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

import static duke.ErrorTypeManager.ERROR_UNKNOWN_TASK_INDICATOR;
import static duke.ErrorTypeManager.ERROR_WITH_FILE;

public class Storage {
    // Specifying file paths in an OS-independent way
    private final String CURRENT_DIRECTORY = System.getProperty("user.dir");

    // Different parts of a task when it is converted to a String
    private final int TASK_TYPE_INDICATOR = 1;
    private final int START_OF_TASK_DESCRIPTION = 7;
    private final String DEADLINE_TASK_DELIMITER = " \\(by:";
    private final String EVENT_TASK_DELIMITER = " \\(at:";

    private final Path dataDirectoryPath;
    private final Path dataFilePath;

    Storage(String filepath) {
        String directoryName = filepath.split("/")[0];
        String fileName = filepath.split("/")[1];

        dataDirectoryPath = Paths.get(CURRENT_DIRECTORY, directoryName);
        dataFilePath = Paths.get(CURRENT_DIRECTORY, directoryName, fileName);
    }

    public void saveTasksData(ArrayList<Task> tasks) throws DukeException {
        ArrayList<String> tasksStringList = new ArrayList<>();

        // Get a string representation of the tasks and store it into an ArrayList
        for (int i = 0; i < tasks.size(); i++) {
            tasksStringList.add(tasks.get(i).toString());
        }

        // Write tasks to data file - a text file
        try {
            Files.write(dataFilePath, tasksStringList);
        } catch (IOException e) {
            throw new DukeException(ERROR_WITH_FILE);
        }
    }

    public ArrayList<Task> loadTasksData() throws DukeException {
        // If folder (data) and file (duke.txt) for saving and loading does not exist yet, create one for user.
        createSaveLocation();

        ArrayList<Task> loadedTasks = new ArrayList<>();
        ArrayList<String> loadedTasksStringList;

        // Requires casting as it only returns a list - superclass of arrayList
        // Read from the saved file - if any
        try {
            loadedTasksStringList = (ArrayList<String>) Files.readAllLines(dataFilePath);
        } catch (IOException e) {
            throw new DukeException(ERROR_WITH_FILE);
        }

        for (String task : loadedTasksStringList) {

            TaskType taskType;

            // Get task type while catching unknown-task-type error
            taskType = getTaskType(task.charAt(TASK_TYPE_INDICATOR));


            // Using enums to check the type of task we are dealing with.
            // Unlikely to go to the default statement since enums are fixed values
            switch(taskType) {
            case TODO:
                loadTodoTask(task, loadedTasks);
                break;
            case DEADLINE:
                loadDeadlineTask(task, loadedTasks);
                break;
            case EVENT:
                loadEventTask(task, loadedTasks);
                break;
            default:
                throw new DukeException(ERROR_UNKNOWN_TASK_INDICATOR);
            }
        }

        return loadedTasks;
    }

    // If folder (data) and file (duke.txt) for saving and loading does not exist yet, create one for user.
    private void createSaveLocation() throws DukeException {
        // Create 'data' folder if it does not already exists
        try {
            if (!Files.exists(dataDirectoryPath)) {
                Files.createDirectory(dataDirectoryPath);
            }
            // Create 'duke.txt' file if it does not already exists
            if (!Files.exists(dataFilePath)) {
                Files.createFile(dataFilePath);
            }
        } catch (IOException e) {
            throw new DukeException(ERROR_WITH_FILE);
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
            throw new DukeException(ERROR_UNKNOWN_TASK_INDICATOR);
        }
    }

    // Identify if task was marked as done before and mark it as done in current session if yes
    private void markTaskDoneIfDone (String task, ArrayList<Task> loadedTasks) {
        if (task.contains(Task.TICK_SYMBOL)) {
            loadedTasks.get(loadedTasks.size() - 1).markAsDone();
        }
    }

    // Exceptions are already handled in Duke.java and hence we will assume these are appropriate tasks
    // No need to notify user that the task has been added too
    private void loadTodoTask(String task, ArrayList<Task> loadedTasks) {
        String taskDescription = task.substring(START_OF_TASK_DESCRIPTION);

        // Load saved to_do task into current tasks
        loadedTasks.add(new Todo(taskDescription));

        // Recover its isDone status
        markTaskDoneIfDone(task, loadedTasks);
    }

    // Deadline tasks should be in the right format since exceptions were handled in Duke.java
    // No need to notify user that the task has been added too
    private void loadDeadlineTask(String task, ArrayList<Task> loadedTasks) {
        String taskDescription = task.substring(START_OF_TASK_DESCRIPTION);

        String[] deadlineTaskParts = taskDescription.split(DEADLINE_TASK_DELIMITER);
        String deadlineTaskDescription = deadlineTaskParts[0];
        // Took the substring of the deadline portion because we want to ignore the closing bracket at the end
        String deadline = deadlineTaskParts[1].substring(0, deadlineTaskParts[1].length() - 1);

        // Load saved deadline task into current tasks
        loadedTasks.add(new Deadline(deadlineTaskDescription, deadline));

        // Recover its isDone status
        markTaskDoneIfDone(task, loadedTasks);
    }

    // Event tasks should be in the right format since exceptions were handled in Duke.java
    // No need to notify user that the task has been added too
    private void loadEventTask(String task, ArrayList<Task> loadedTasks) {
        String taskDescription = task.substring(START_OF_TASK_DESCRIPTION);

        String[] eventTaskParts = taskDescription.split(EVENT_TASK_DELIMITER);
        String eventTaskDescription = eventTaskParts[0];
        // Took the substring of the deadline portion because we want to ignore the closing bracket at the end
        String eventTime = eventTaskParts[1].substring(0, eventTaskParts[1].length() - 1);

        // Load saved event task into current tasks
        loadedTasks.add(new Event(eventTaskDescription, eventTime));

        // Recover its isDone status
        markTaskDoneIfDone(task, loadedTasks);
    }
}
