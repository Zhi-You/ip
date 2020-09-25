package duke.commands;

import duke.exception.DukeException;
import duke.data.Storage;
import duke.task.TaskList;

/**
 * Lists all tasks in the TaskList to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
    }

    /**
     * Executes the command and prints the lists of tasks to user.
     *
     * @param taskList TaskList object with user's list of tasks and methods to operate on tasks.
     * @param storage Used to save user's tasks.
     * @throws DukeException If there is an error saving tasks to the file.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.printTaskList();
        super.execute(taskList, storage);
    }
}
