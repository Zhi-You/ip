package duke.parser;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;

import duke.exception.DukeException;

import static duke.exception.ErrorTypeManager.ERROR_UNKNOWN_COMMAND;

/**
 * Represents a parser that parses user inputs into commands.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput Full user input.
     * @return The command object based on the user input.
     * @throws DukeException If the command parsed is an unknown command.
     */
    public static Command parse(String userInput) throws DukeException {
        String command = getCommandFromInput(userInput);
        String taskDescription = getTaskDescriptionFromInput(userInput);

        switch (command) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DoneCommand.COMMAND_WORD:
            return new DoneCommand(taskDescription);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(taskDescription);
        case TodoCommand.COMMAND_WORD:
            return new TodoCommand(taskDescription);
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommand(taskDescription);
        case EventCommand.COMMAND_WORD:
            return new EventCommand(taskDescription);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(taskDescription);
        default:
            throw new DukeException(ERROR_UNKNOWN_COMMAND);
        }
    }

    private static String getCommandFromInput(String userInput) {
        // Splits user input into different words to distinguish the command from the task's information
        String[] inputParts = userInput.split(" ");

        String command = inputParts[0].toLowerCase();
        return command;
    }

    private static String getTaskDescriptionFromInput(String userInput) {
        String[] inputParts = userInput.split(" ");

        String taskDescription = "";

        // Forms the task description after splitting user input into words and ignoring the command word
        for (int i = 1; i < inputParts.length; i++) {
            taskDescription = String.join(" ", taskDescription, inputParts[i]);
        }
        taskDescription = taskDescription.trim();

        return taskDescription;
    }
}
