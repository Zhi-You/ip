package duke.task;

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
