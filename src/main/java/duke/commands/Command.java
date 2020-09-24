package duke.commands;

import duke.exception.DukeException;
import duke.data.Storage;
import duke.task.TaskList;


public abstract class Command {
    protected boolean isExit = false;
    protected String taskDescription;

    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList taskList, Storage storage) throws DukeException {
        storage.saveTasksData(taskList.getTasks());
    }
}
