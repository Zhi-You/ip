package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.printTaskList();
        super.execute(taskList, storage);
    }
}
