public class Task {
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
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
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
