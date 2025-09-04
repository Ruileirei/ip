package Jerome.ui;

import java.util.List;
import java.util.Scanner;

import Jerome.task.Task;

/**
 * Handles user interface interactions and displays messages to the user.
 * Responsible for displaying the welcome message, task list, errors, and more.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Displays the welcome message when the application starts.
     */
    public static String welcomeText() {
        return "Wassup, I'm Jerome!\nWhat can I do for you?";

    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public String goodbyeText() {
        return "Bye gng. Dap a homie up before you go will ya!\n";
    }

    /**
     * Displays an error message with a custom message.
     */
    public void errorText(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays a success message when a task is added.
     */
    public String successfulAddText(Task t, int size) {
        return "Gotchu mahomes! I added:\n" + t + "\nThere are now " + size + " tasks!\n";
    }

    /**
     * Displays a success message when a task is deleted.
     */
    public String successfulDeleteText(Task t, int size) {
        return "Alright matey! I have removed this task:\n" + t + "\nThere are now " + size + " task(s)!\n";
    }

    /**
     * Prints the task list to the console.
     */
    public String taskListText(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "You trippin...there aint no tasks";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are your tasks broski:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    public String successfulMarkText(Task task) {
        return "Nice! I have marked the following task as complete!\n" + task + "\n";
    }

    public String successfulUnmarkText(Task task) {
        return "Nice! I have marked the following task as complete!\n" + task + "\n";
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
}
