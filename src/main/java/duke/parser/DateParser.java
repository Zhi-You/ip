package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateParser {


    public static String parse(String taskDescription) {
        // guard clause
        if (!checkIfDescriptionHasDate(taskDescription)) {
            return taskDescription;         // no change
        }

        String dateInput = getDateSubstring(taskDescription);
        String formattedDate;

        try {
            formattedDate = convertDateFormat(dateInput);
        } catch (DateTimeParseException e) {
            return taskDescription;
        }
        String updatedTaskDescription = taskDescription.replace(dateInput, formattedDate);

        return updatedTaskDescription;
    }

    //yyyy-mm-dd
    // perform two checks to see if user input include the given format
    // hyphen first then substring length
    private static boolean checkIfDescriptionHasDate(String taskDescription) {
        if (!checkIfDescriptionHasHyphen(taskDescription)) {
            return false;
        }

        //  might have index out of bound exception // can have a try and catch it here and just return false in catch
        //String dateInput = taskDescription.substring(indexOfHyphen - 4, indexOfHyphen + 5);

        // just catch the exception and return false if theres the exception
        try {
            String dateInput = getDateSubstring(taskDescription);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        return true;
    }

    private static boolean checkIfDescriptionHasHyphen(String taskDescription) {
        return (taskDescription.contains("-"));
    }

    // throw exception over here for index out of bound
    private static String getDateSubstring(String taskDescription) throws IndexOutOfBoundsException {
        int indexOfHyphen = taskDescription.indexOf("-");

        String dateInput = taskDescription.substring(indexOfHyphen - 4, indexOfHyphen + 6);

        return dateInput;
    }

    // exception error message : invalid date entered
    private static String convertDateFormat(String dateInput) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(dateInput);

        return(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
