package duke.commands;

import duke.exception.DukeException;
import duke.data.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.printTaskList();
        super.execute(taskList, storage);
    }
}
