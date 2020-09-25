package duke.task;

/**
 * Represents a Deadline task. A <code>Deadline</code> object corresponds to
 * a task represented by its done status, task description and deadline
 * e.g., <code>[D][✓] return book  (by: Sunday)</code>
 */
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
