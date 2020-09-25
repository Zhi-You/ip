package duke.commands;

import duke.exception.DukeException;
import duke.data.Storage;
import duke.task.TaskList;

import static duke.exception.ErrorTypeManager.ERROR_FIND_TASK_EMPTY_DESCRIPTION;

/**
 * Finds all tasks that contains a certain keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public FindCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        checkTaskDescriptionNotEmpty();
        taskList.findTasksAndPrint(taskDescription);

        super.execute(taskList, storage);
    }

    private void checkTaskDescriptionNotEmpty() throws DukeException {
        if (taskDescription.isBlank()) {
            throw new DukeException(ERROR_FIND_TASK_EMPTY_DESCRIPTION);
        }
    }
}