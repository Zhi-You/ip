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

public class Parser {

    public static Command parse(String userInput) throws DukeException {
        String command = getCommandFromInput(userInput);
        String taskDescription = getTaskDescriptionFromInput(userInput);

        // Duke reads user commands and either show task list, mark task as done or add task
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

    // Extracts command word from user inputs to decide what Duke will do
    private static String getCommandFromInput(String userInput) {
        // Splits user input into different words to distinguish between command and task's information
        String[] inputParts = userInput.split(" ");

        // User's command can be found in the first word of user's inputs
        return inputParts[0].toLowerCase();
    }

    // Extracts task information from user inputs to decide what kind of task to add to list
    private static String getTaskDescriptionFromInput(String userInput) {
        // Splits user input into different words to distinguish between command and task's information
        String[] inputParts = userInput.split(" ");

        String taskDescription = "";

        // Gets the informative parts from user input after the command (first word)
        for (int i = 1; i < inputParts.length; i++) {
            taskDescription = String.join(" ", taskDescription, inputParts[i]);
        }
        taskDescription = taskDescription.trim();

        return taskDescription;
    }
}
