package duke.task;

/**
 * Represents a Todo task. A <code>Todo</code> object corresponds to
 * a task represented by its done status and task description
 * e.g., <code>[T][✓] join sports club</code>
 */
public class Todo extends Task {

    private final String TASK_INDICATOR = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TASK_INDICATOR + super.toString();
    }
}
