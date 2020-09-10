public class Event extends Task {

    private final String EVENT_TASK_INDICATOR = "[E]";

    protected String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return EVENT_TASK_INDICATOR + super.toString() + " (at:" + eventTime + ")";
    }
}
