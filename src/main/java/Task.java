public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    // Setter method for isDone
    public void markAsDone() {
        this.isDone = true;
    }
}