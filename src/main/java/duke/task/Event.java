package duke.task;

import java.time.LocalDate;

/**
 * Represents an Event task. An <code>Event</code> object corresponds to
 * a task represented by its done status, task description and event timing.
 * A date specified in event timing would be parsed into a LocalDate object if it is of the form: 'yyyy-mm-dd'.
 * Example of an Event task: <code>[E][✓] Christmas party  (at: Dec 24 2020 11pm)</code>
 */
public class Event extends Task {

    private final String TASK_INDICATOR = "[E]";

    private String eventTime;
    private LocalDate date;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Returns the event time specified by the user as a string.
     *
     * @return Event time as a string.
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * Updates the event time to include the formatted date string if a date was passed
     * into the task description in a 'yyyy-mm-dd' format.
     *
     * @param eventTime Event time description.
     */
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * Sets a LocalDate object to the task's date attribute if a date string with the 'yyyy-mm-dd' format
     * is found in the task description. If no string of such format is found, null will be set.
     *
     * @param date Date specified by the user for this task.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the LocalDate object tied to this task.
     *
     * @return Date for this task.
     */
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return TASK_INDICATOR + super.toString() + " (at:" + eventTime + ")";
    }
}
