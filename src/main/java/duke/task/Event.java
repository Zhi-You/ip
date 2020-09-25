package duke.task;

/**
 * Represents an Event task. An <code>Event</code> object corresponds to
 * a task represented by its done status, task description and event timing
 * e.g., <code>[E][✓] project meeting  (at: Mon 2-4pm)</code>
 */
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
