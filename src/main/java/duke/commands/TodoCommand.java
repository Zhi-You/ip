package duke.commands;

import duke.exception.DukeException;
import duke.data.Storage;
import duke.task.TaskList;

import static duke.exception.ErrorTypeManager.ERROR_TODO_EMPTY_DESCRIPTION;

/**
 * Adds a Todo task to the user's task list.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the command and adds a Todo task if its description is given.
     *
     * @param taskList TaskList object with user's list of tasks and methods to operate on tasks.
     * @param storage Used to save user's tasks.
     * @throws DukeException If there is an error adding task or saving tasks to the file.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        checkTaskDescriptionNotEmpty();
        taskList.addTodoTask(taskDescription);

        super.execute(taskList, storage);
    }

    private void checkTaskDescriptionNotEmpty() throws DukeException {
        if (taskDescription.isBlank()) {
            throw new DukeException(ERROR_TODO_EMPTY_DESCRIPTION);
        }
    }
}
