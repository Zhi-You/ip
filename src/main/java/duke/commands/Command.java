package duke.commands;

import duke.exception.DukeException;
import duke.data.Storage;
import duke.task.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected boolean isExit = false;
    protected String taskDescription;

    /**
     * Checks if the last command entered by the user is the exit command.
     *
     * @return An indication of whether the exit command was given.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command and saves the latest version of the user's tasks
     *
     * @param taskList TaskList object with user's list of tasks and methods to operate on tasks
     * @param storage Used to save user's tasks
     * @throws DukeException If there is an error saving tasks to the file
     */
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        storage.saveTasksData(taskList.getTasks());
    }
}
