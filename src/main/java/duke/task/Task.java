package duke.task;

public class Task {
    private static final String TICK_SYMBOL = "[\u2713]";
    private static final String CROSS_SYMBOL = "[\u2718]";
    protected String description;
    protected boolean isDone;

    // Class-level member to keep track of the total number of tasks created
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        isDone = false;
        taskCount++;
    }

    // Class-level method to get taskCount
    public static int getTaskCount() {
        return taskCount;
    }

    public String getStatusIcon() {
        return (isDone ? TICK_SYMBOL : CROSS_SYMBOL); //return tick or X symbols
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    // Setter method for isDone
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
