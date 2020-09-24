package duke.commands;

import duke.exception.DukeException;
import duke.data.Storage;
import duke.task.TaskList;


public abstract class Command {
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        storage.saveTasksData(taskList.getTasks());
    }

    protected boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }
}
