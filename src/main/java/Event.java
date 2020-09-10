public class Event extends Task {

    private final String EVENT_TASK_INDICATOR = "[E]";

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return EVENT_TASK_INDICATOR + super.toString() + " (at:" + at + ")";
    }
}
