package duke.task;

public class Task {
    public static final String TICK_SYMBOL = "[\u2713]";
    public static final String CROSS_SYMBOL = "[\u2718]";
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        isDone = false;
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
