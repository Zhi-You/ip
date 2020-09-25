package duke.task;

import java.time.LocalDate;

/**
 * Represents a Deadline task. A <code>Deadline</code> object corresponds to
 * a task represented by its done status, task description and deadline.
 * A date specified in deadline would be parsed into a LocalDate object if it is of the form: 'yyyy-mm-dd'.
 * Example of a Deadline task: <code>[D][✓] return book  (by: Sep 24 2020 10pm)</code>
 */
public class Deadline extends Task {

    private final String TASK_INDICATOR = "[D]";

    private String deadline;
    private LocalDate date;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline specified by the user as a string.
     *
     * @return Deadline as a string.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Updates the deadline to include the formatted date string if a date was passed
     * into the task description in a 'yyyy-mm-dd' format.
     *
     * @param deadline Deadline description.
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
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
        return TASK_INDICATOR + super.toString() + " (by:" + deadline + ")";
    }
}
