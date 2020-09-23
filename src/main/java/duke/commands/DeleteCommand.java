package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

import static duke.ErrorTypeManager.ERROR_DELETETASK_EMPTY_DESCRIPTION;


public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private String taskDescription;

    public DeleteCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.deleteTask(taskDescription);
        checkTaskDescriptionNotEmpty();

        super.execute(taskList, storage);
    }

    private void checkTaskDescriptionNotEmpty() throws DukeException {
        if (taskDescription.isBlank()) {
            throw new DukeException(ERROR_DELETETASK_EMPTY_DESCRIPTION);
        }
    }
}
