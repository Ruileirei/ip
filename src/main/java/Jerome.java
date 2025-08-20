import java.util.Scanner;
import java.util.ArrayList;

public class Jerome {
    public static void main(String[] args) {
        String line = "_____________________________";
        ArrayList<Task> ls = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        //introduction
        System.out.println(line + "\nHello, I'm Jerome!\n" +  "What can I do for you?\n" + line);

        //Responses
        while (true) {
            //user input
            String response = sc.nextLine();
            if (response.equals("bye")) {
                break;
            }
            if (response.isEmpty()) {
                //ignore empty responses
                continue;
            } else if (response.equals("list")) {
                //if task list is empty
                if (ls.isEmpty()) {
                    System.out.println("There is no tasks currently :/");
                } else {
                    //print all tasks if list is not empty
                    System.out.println("Here are you tasks: ");
                    for (int i = 0; i < ls.size(); i++) {
                        System.out.println((i + 1) + ". " + ls.get(i));
                    }
                }
            } else if (response.startsWith("unmark") || response.startsWith("mark")) {
                //marking and unmarking a task
                String[] input = response.split(" ");
                try {
                    int ind = Integer.parseInt(input[1]) - 1;
                    if (ind < 0 || ind >= ls.size()) {
                        System.out.println("Sorry! I can't find that task :(");
                        continue;
                    }
                    if (response.contains("unmark")) {
                        ls.get(Integer.parseInt(input[1]) - 1).unmark();
                    } else {
                        ls.get(Integer.parseInt(input[1]) - 1).mark();
                    }
                } catch (NumberFormatException e) {
                    //if input is mark __ where it is not an integer.
                    System.out.println("Sorry! I can't find that task :(");
                }
            } else {
                //add task, echo
                Task task = new Task(response);
                ls.add(task);
                System.out.println(line + "\n" + "added: " + response + "\n" + line);
            }
        }
        //ending
        System.out.println("Bye. Hope to see you soon!\n" + line);

    }
}
