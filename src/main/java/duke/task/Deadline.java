package duke.task;

public class Deadline extends Task {

    private final String TASK_INDICATOR = "[D]";

    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return TASK_INDICATOR + super.toString() + " (by:" + deadline + ")";
    }
}
