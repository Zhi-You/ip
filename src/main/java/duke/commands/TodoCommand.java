package duke.commands;

import duke.exception.DukeException;
import duke.data.Storage;
import duke.task.TaskList;

import static duke.exception.ErrorTypeManager.ERROR_TODO_EMPTY_DESCRIPTION;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private String taskDescription;

    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

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
