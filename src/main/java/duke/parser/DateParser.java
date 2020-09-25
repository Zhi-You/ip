package duke.parser;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser that parses date inputs into LocalDate objects.
 */
public class DateParser {
    private static String dateSubstringInInput;

    /**
     * Searches a task's description for a string of the format 'yyyy-mm-dd' which represents a date
     * specified by the user and returns a LocalDate object from it.
     * If no such string is found, null is returned instead.
     *
     * @param taskDescription Task's description which might contain a string of the form 'yyyy-mm-dd'.
     * @return LocalDate object corresponding to the date specified by user in the task description.
     */
    public static LocalDate getTaskDate(String taskDescription) {

        String[] taskDescriptionParts = taskDescription.split(" ");
        LocalDate dateFoundFromInput = null;

        // Searches for a word with 'yyyy-mm-dd' format that could be parsed into a LocalDate object.
        for (String substring : taskDescriptionParts) {
            try {
                dateFoundFromInput = LocalDate.parse(substring);
                dateSubstringInInput = substring;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return dateFoundFromInput;
    }

    /**
     * Updates the date description of a Deadline or Event task to reflect the formatted date string.
     * If task description did not contain a string of the format 'yyyy-mm-dd', it is assumed that
     * the user did no specify any task and thus task description will not change.
     *
     * @param taskDescription Task description to be updated.
     * @param dateInput Parsed date from task description.
     * @return Updated date description.
     */
    public static String newDateDescription(String taskDescription, LocalDate dateInput) {
        String formattedDate = dateInput.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        String newDescription = taskDescription.replace(dateSubstringInInput, formattedDate);

        return newDescription;
    }
}
