package duke.data;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static duke.exception.ErrorTypeManager.ERROR_UNKNOWN_TASK_TYPE;
import static duke.exception.ErrorTypeManager.ERROR_WITH_FILE;

/**
 * Represents a storage for user's list of tasks in the machine's local filesystem.
 * A <code>Storage</code> object corresponds to the location where user's data is saved, represented by a
 * filepath, e.g., <code>data/tasks.txt</code>
 */
public class Storage {
    private final int TASK_TYPE_INDICATOR = 1;
    private final int START_OF_TASK_DESCRIPTION = 7;
    private final String DEADLINE_TASK_DELIMITER = " \\(by:";
    private final String EVENT_TASK_DELIMITER = " \\(at:";

    /** Filepath of user's current directory, retrieved in an OS-independent way */
    private final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private final Path dataDirectoryPath;
    private final Path dataFilePath;

    /**
     * Creates a Storage object to save data into the specified filepath.
     *
     * @param filepath Location of where data should be stored.
     */
    public Storage(String filepath) {
        String directoryName = filepath.split("/")[0];
        String fileName = filepath.split("/")[1];

        dataDirectoryPath = Paths.get(CURRENT_DIRECTORY, directoryName);
        dataFilePath = Paths.get(CURRENT_DIRECTORY, directoryName, fileName);
    }

    /**
     * Saves user's list of tasks into a text file.
     *
     * @param tasks List of Task objects to be saved.
     * @throws DukeException If there is an error while writing to the file.
     */
    public void saveTasksData(ArrayList<Task> tasks) throws DukeException {
        ArrayList<String> tasksStringList = new ArrayList<>();

        // Gets a list of string representations of task objects that are to be stored into the text file
        for (int i = 0; i < tasks.size(); i++) {
            tasksStringList.add(tasks.get(i).toString());
        }

        try {
            Files.write(dataFilePath, tasksStringList);
        } catch (IOException e) {
            throw new DukeException(ERROR_WITH_FILE);
        }
    }

    /**
     * Loads tasks from a text file located in the filepath specified in the Storage object.
     * If tasks are formatted properly in the save file, tasks will be loaded appropriately
     * according to their type (Deadline, Event or Todo).
     *
     * @return List of tasks.
     * @throws DukeException If there is an error reading from the file or if a task type is incorrect.
     */
    public ArrayList<Task> loadTasksData() throws DukeException {
        createSaveFileIfNotExist();

        ArrayList<Task> loadedTasks = new ArrayList<>();
        ArrayList<String> loadedTasksStringList;

        try {
            // Casting into an ArrayList because readAllLines returns a List
            loadedTasksStringList = (ArrayList<String>) Files.readAllLines(dataFilePath);
        } catch (IOException e) {
            throw new DukeException(ERROR_WITH_FILE);
        }

        for (String task : loadedTasksStringList) {
            // Using a TaskType enum to identify what type the task to be loaded is
            TaskType taskType;
            taskType = getTaskType(task.charAt(TASK_TYPE_INDICATOR));

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
                throw new DukeException(ERROR_UNKNOWN_TASK_TYPE);
            }
        }
        return loadedTasks;
    }

    /**
     * Creates the data folder or file if any of them does not already exist.
     *
     * @throws DukeException If there is an error creating the folder or the file.
     */
    private void createSaveFileIfNotExist() throws DukeException {
        try {
            if (!Files.exists(dataDirectoryPath)) {
                Files.createDirectory(dataDirectoryPath);
            }
            if (!Files.exists(dataFilePath)) {
                Files.createFile(dataFilePath);
            }
        } catch (IOException e) {
            throw new DukeException(ERROR_WITH_FILE);
        }
    }

    private static TaskType getTaskType(char taskTypeIndicator) throws DukeException {
        switch(taskTypeIndicator) {
        case 'T':
            return TaskType.TODO;
        case 'D':
            return TaskType.DEADLINE;
        case 'E':
            return TaskType.EVENT;
        default:
            throw new DukeException(ERROR_UNKNOWN_TASK_TYPE);
        }
    }

    private void markTaskDoneIfDone (String task, ArrayList<Task> loadedTasks) {
        if (task.contains(Task.TICK_SYMBOL)) {
            loadedTasks.get(loadedTasks.size() - 1).markAsDone();
        }
    }

    private void loadTodoTask(String task, ArrayList<Task> loadedTasks) {
        String taskDescription = task.substring(START_OF_TASK_DESCRIPTION);

        Todo newTodoTask = new Todo(taskDescription);
        loadedTasks.add(newTodoTask);

        markTaskDoneIfDone(task, loadedTasks);
    }

    private void loadDeadlineTask(String task, ArrayList<Task> loadedTasks) {
        String taskDescription = task.substring(START_OF_TASK_DESCRIPTION);

        String[] deadlineTaskParts = taskDescription.split(DEADLINE_TASK_DELIMITER);
        String deadlineTaskDescription = deadlineTaskParts[0];
        // Ignores the closing bracket of the saved task in the text file by taking length of substring - 1
        String deadline = deadlineTaskParts[1].substring(0, deadlineTaskParts[1].length() - 1);

        Deadline newDeadlineTask = new Deadline(deadlineTaskDescription, deadline);
        loadedTasks.add(newDeadlineTask);

        markTaskDoneIfDone(task, loadedTasks);
    }

    private void loadEventTask(String task, ArrayList<Task> loadedTasks) {
        String taskDescription = task.substring(START_OF_TASK_DESCRIPTION);

        String[] eventTaskParts = taskDescription.split(EVENT_TASK_DELIMITER);
        String eventTaskDescription = eventTaskParts[0];
        // Ignores the closing bracket of the saved task in the text file by taking length of substring - 1
        String eventTime = eventTaskParts[1].substring(0, eventTaskParts[1].length() - 1);

        Event newEventTask = new Event(eventTaskDescription, eventTime);
        loadedTasks.add(newEventTask);

        markTaskDoneIfDone(task, loadedTasks);
    }
}
