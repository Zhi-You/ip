package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

import static duke.ErrorTypeManager.ERROR_MARKTASKASDONE_EMPTY_DESCRIPTION;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    private String taskDescription;

    public DoneCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

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