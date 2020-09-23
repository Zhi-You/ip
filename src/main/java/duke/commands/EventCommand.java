package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

import static duke.ErrorTypeManager.ERROR_EVENT_EMPTY_DESCRIPTION;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private String taskDescription;

    public EventCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        checkTaskDescriptionNotEmpty();
        taskList.addEventTask(taskDescription);

        super.execute(taskList, storage);
    }

    private void checkTaskDescriptionNotEmpty() throws DukeException {
        if (taskDescription.isBlank()) {
            throw new DukeException(ERROR_EVENT_EMPTY_DESCRIPTION);
        }
    }
}
