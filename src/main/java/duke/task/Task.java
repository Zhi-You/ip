package duke.task;

/**
 * Represents a task created by the user. A <code>Task</code> object is represented by
 * its done status and task description.
 */
public class Task {
    public static final String TICK_SYMBOL = "[\u2713]";
    public static final String CROSS_SYMBOL = "[\u2718]";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns a tick or a cross symbol depending on the task's done status.
     *
     * @return Indication of the done status of a task.
     */
    public String getStatusIcon() {
        return (isDone ? TICK_SYMBOL : CROSS_SYMBOL);
    }

    /**
     * Returns the description of a task.
     *
     * @return Task information.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the done status of a task to be done.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
