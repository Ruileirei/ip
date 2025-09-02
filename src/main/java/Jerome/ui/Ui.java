package Jerome.ui;

import java.util.List;
import java.util.Scanner;

import Jerome.task.Task;

/**
 * Handles user interface interactions and displays messages to the user.
 * Responsible for displaying the welcome message, task list, errors, and more.
 */
public class Ui {
    private static final String LINE = "_____________________________";
    private final Scanner sc = new Scanner(System.in);

    /**
     * Displays the welcome message when the application starts.
     */
    public void welcomeText() {
        System.out.println(LINE
                + "\nWassup, I'm Jerome.Jerome!\nWhat can I do for you?\n"
                + LINE);
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public void goodbyeText() {
        System.out.println("Bye gng. Dap a homie up before you go will ya!\n"
                + LINE);
    }

    /**
     * Prints a horizontal line for separation.
     */
    public void lineText() {
        System.out.println(LINE);
    }

    /**
     * Displays an error message with a custom message.
     */
    public void errorText(String msg) {
        System.out.println(LINE
                + "\n" + msg + "\n"
                + LINE);
    }

    /**
     * Displays a success message when a task is added.
     */
    public void successfulAddText(Task t, int size) {
        System.out.println(LINE
                + "\nGotchu mahomes! I added:\n" + t + "\nThere are now " + size + " tasks!\n"
                + LINE);
    }

    /**
     * Displays a success message when a task is deleted.
     */
    public void successfulDeleteText(Task t, int size) {
        System.out.println(LINE
                + "\nAlright matey! I have removed this task:\n" + t + "\nThere are now " + size + " task(s)!\n"
                + LINE);
    }

    /**
     * Prints the task list to the console.
     */
    public void printTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You trippin...there aint no tasks");
        } else {
            System.out.println("Here are your tasks broski:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Displays an error message when the index is invalid.
     */
    public void invalidIndexText() {
        errorText("Mah bad dude! I can't find that task...");
    }

    /**
     * Displays an error message when the date/time format is invalid.
     */
    public void invalidDateTimeText() {
        errorText("Sorry! The date/time must be in format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
    }

    /**
     * Reads the user's command from the console.
     */
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        }
        return "bye";
    }

    /**
     * Closes the scanner object used for reading user input.
     */
    public void close() {
        sc.close();
    }

    /**
     * Displays an error message when a specific error occurs.
     */
    public void showError(String msg) {
        errorText(msg);
    }

    public void print(String text) {
        System.out.println(text);
    }
}
