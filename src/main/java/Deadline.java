public class Deadline extends Task {

    private final String DEADLINE_TASK_INDICATOR = "[D]";

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return DEADLINE_TASK_INDICATOR + super.toString() + " (by:" + by + ")";
    }
}
