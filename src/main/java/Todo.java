public class Todo extends Task {

    private final String TODO_TASK_INDICATOR = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TODO_TASK_INDICATOR + super.toString();
    }
}
