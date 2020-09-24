package duke;

import duke.commands.Command;
import duke.data.Storage;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.exception.ErrorTypeManager.ERROR_NO_DATA_TO_LOAD;

public class Duke {

    private static final String FILE_PATH = "data/tasks.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksData());
        } catch (DukeException e) {
            if (e.getMessage().equals(ERROR_NO_DATA_TO_LOAD)) {
                ui.printNoDataLoaded();
            } else {
                ui.printDukeErrorMessage(e.getMessage());
            }
            tasks = new TaskList();
        }
    }

    private void start() {
        ui.printWelcomeMessage();
    }

    private void run() {
        start();
        runCommandLoopUntilExitCommand();
        end();
    }

    private void end() {
        ui.printExitMessage();
    }

    private void runCommandLoopUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command command = Parser.parse(userInput);
                command.execute(tasks, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printDukeErrorMessage(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
