package duke.commands;

import duke.exception.DukeException;
import duke.data.Storage;
import duke.task.TaskList;

import static duke.exception.ErrorTypeManager.ERROR_DELETE_TASK_EMPTY_DESCRIPTION;

/**
 * Deletes a task from the user's task list, according to an index given by user.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    /**
     * @param taskDescription Description of the task to be deleted in the form of its index.
     */
    public DeleteCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the command and deletes a task if its description is given.
     *
     * @param taskList TaskList object with user's list of tasks and methods to operate on tasks.
     * @param storage Used to save user's tasks.
     * @throws DukeException If there is an error deleting a task or saving the remaining tasks to the file.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        checkTaskDescriptionNotEmpty();
        taskList.deleteTask(taskDescription);

        super.execute(taskList, storage);
    }

    private void checkTaskDescriptionNotEmpty() throws DukeException {
        if (taskDescription.isBlank()) {
            throw new DukeException(ERROR_DELETE_TASK_EMPTY_DESCRIPTION);
        }
    }
}
