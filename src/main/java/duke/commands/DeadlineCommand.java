package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

import static duke.ErrorTypeManager.ERROR_DEADLINE_EMPTY_DESCRIPTION;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private String taskDescription;

    public DeadlineCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        checkTaskDescriptionNotEmpty();
        taskList.addDeadlineTask(taskDescription);

        super.execute(taskList, storage);
    }

    private void checkTaskDescriptionNotEmpty() throws DukeException {
        if (taskDescription.isBlank()) {
            throw new DukeException(ERROR_DEADLINE_EMPTY_DESCRIPTION);
        }
    }
}