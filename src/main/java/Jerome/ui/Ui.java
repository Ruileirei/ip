package Jerome.ui;

import Jerome.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private final String LINE = "_____________________________";
    private final Scanner sc = new Scanner(System.in);

    public void welcomeText() {
        System.out.println(LINE
                + "\nWassup, I'm Jerome.Jerome!\nWhat can I do for you?\n"
                + LINE);
    }

    public void goodbyeText() {
        System.out.println("Bye gng. Dap a homie up before you go will ya!\n"
                + LINE);
    }

    public void lineText() {
        System.out.println(LINE);
    }

    public void errorText(String msg) {
        System.out.println(LINE
                + "\n" + msg + "\n"
                + LINE);
    }

    public void successfulAddText(Task t, int size) {
        System.out.println(LINE
                + "\nGotchu mahomes! I added:\n" + t + "\nThere are now " + size + " tasks!\n"
                + LINE);
    }

    public void successfulDeleteText(Task t, int size) {
        System.out.println(LINE
                + "\nAlright matey! I have removed this task:\n" + t + "\nThere are now " + size + " task(s)!\n"
                + LINE);
    }

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

    public void invalidIndexText() {
        errorText("Mah bad dude! I can't find that task...");
    }

    public void invalidDateTimeText() {
        errorText("Sorry! The date/time must be in format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
    }

    public String readCommand() {
        if(sc.hasNextLine()) {
            return sc.nextLine();
        }
        return "bye";
    }

    public void close() {
        sc.close();
    }

    public void showError(String msg) {
        errorText(msg);
    }

    public void print(String text) {
        System.out.println(text);
    }
}
