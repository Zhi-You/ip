package duke;

import duke.commands.Command;
import duke.data.Storage;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.exception.ErrorTypeManager.ERROR_NO_DATA_TO_LOAD;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    /** Location of the save file */
    private static final String FILE_PATH = "data/tasks.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Loads any previously saved task list for the current Duke execution.
     * Notifies user if saved data was successfully loaded or if there are no saved data found.
     * Also notifies user if an error has occurred when loading saved data from file.
     *
     * @param filePath Location of the file to load from.
     */
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

    /** Runs the program until termination.  */
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

    /**
     * Starts the execution of Duke.
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
