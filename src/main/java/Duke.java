import java.util.Scanner;

public class Duke {

    public static void printHorizontalLine() {
        String horizontalLine = "________________________________________________";
        System.out.println(horizontalLine);
    }

    // Echos commands entered by the user, and exits when the user types bye
    public static void getInputAndEcho() {
        Scanner in = new Scanner(System.in);
        String command;

        // Get initial input
        command = in.nextLine();

        while (!command.equalsIgnoreCase("bye")) {

            printHorizontalLine();
            // Echo out the command entered by the user
            System.out.println(command);
            printHorizontalLine();

            // Gets another input
            command = in.nextLine();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Greet, Echo, Exit
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
        getInputAndEcho();
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
