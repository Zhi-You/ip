package duke.commands;

import duke.exception.DukeException;
import duke.data.Storage;
import duke.task.TaskList;

import static duke.exception.ErrorTypeManager.ERROR_EVENT_EMPTY_DESCRIPTION;

/**
 * Adds an Event task to the user's task list.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    /**
     * @param taskDescription Description of the task and its event time.
     */
    public EventCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the command and adds an Event task if its description is given.
     *
     * @param taskList TaskList object with user's list of tasks and methods to operate on tasks.
     * @param storage Used to save user's tasks.
     * @throws DukeException If there is an error adding task or saving tasks to the file.
     */
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
