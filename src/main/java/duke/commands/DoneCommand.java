package duke.commands;

import duke.exception.DukeException;
import duke.data.Storage;
import duke.task.TaskList;

import static duke.exception.ErrorTypeManager.ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION;

/**
 * Marks a task in the task list as done, according to an index given by user.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    /**
     * @param taskDescription Description of the task to be marked as done in the form of its index.
     */
    public DoneCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the command and marks a task as done, if its description is given.
     *
     * @param taskList TaskList object with user's list of tasks and methods to operate on tasks.
     * @param storage Used to save user's tasks
     * @throws DukeException If there is an error marking a task as done or saving the tasks to the file.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        checkTaskDescriptionNotEmpty();
        taskList.markTaskAsDone(taskDescription);

        super.execute(taskList, storage);
    }

    private void checkTaskDescriptionNotEmpty() throws DukeException {
        if (taskDescription.isBlank()) {
            throw new DukeException(ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION);
        }
    }
}
