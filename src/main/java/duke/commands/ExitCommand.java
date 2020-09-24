package duke.commands;


public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {
        isExit = true;
    }
}
