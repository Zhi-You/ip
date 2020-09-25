package duke.task;

public class Event extends Task {

    private final String TASK_INDICATOR = "[E]";

    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return TASK_INDICATOR + super.toString() + " (at:" + eventTime + ")";
    }
}
