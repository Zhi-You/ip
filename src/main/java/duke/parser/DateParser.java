package duke.parser;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {

    private static String dateSubstringInInput;

    // if not dateFoundFromInput not null, then store the localDate into the attribute
    // of the task object
    public static LocalDate getTaskDate(String taskDescription) {

        String[] taskDescriptionParts = taskDescription.split(" ");
        LocalDate dateFoundFromInput = null;

        // try to parse until it receives a substring of the correct format
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

    // exception error message : invalid date entered
    public static String newDateDescription(String taskDescription, LocalDate dateInput) {

        String formattedDate = dateInput.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        String newDescription = taskDescription.replace(dateSubstringInInput, formattedDate);

        return newDescription;
    }
}
