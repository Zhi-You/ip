package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {

    private final String TASK_INDICATOR = "[D]";

    private String deadline;

    private LocalDate date;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return TASK_INDICATOR + super.toString() + " (by:" + deadline + ")";
    }
}
