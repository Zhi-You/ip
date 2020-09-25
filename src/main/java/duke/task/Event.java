package duke.task;

import java.time.LocalDate;

/**
 * Represents an Event task. An <code>Event</code> object corresponds to
 * a task represented by its done status, task description and event timing
 * e.g., <code>[E][✓] project meeting  (at: Mon 2-4pm)</code>
 */
public class Event extends Task {

    private final String TASK_INDICATOR = "[E]";

    private String eventTime;

    private LocalDate date;

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return TASK_INDICATOR + super.toString() + " (at:" + eventTime + ")";
    }
}
