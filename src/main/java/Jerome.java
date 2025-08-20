import java.util.Scanner;
import java.util.ArrayList;

public class Jerome {
    public static void main(String[] args) {
        String line = "_____________________________";
        ArrayList<String> ls = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        //introduction
        System.out.println(line + "\nHello, I'm Jerome!\n" +  "What can I do for you?\n" + line);

        //user input
        String response = sc.nextLine();

        //Responses
        while (!response.equals("bye")) {
            if (response.equals("list")) {
                //if task list is empty
                if (ls.size() == 0) {
                    System.out.println("There is no tasks currently :/");
                } else {
                    //print all tasks if list is not empty
                    System.out.println("Here are you tasks: ");
                    for (String e : ls) {
                        System.out.println(ls.indexOf(e) + 1 + ". " + e);
                    }
                }
            } else {
                //add task, echo
                ls.add(response);
                System.out.println(line + "\n" + "added: " + response + "\n" + line);
            }
            response = sc.nextLine();
        }

        //ending
        System.out.println("Bye. Hope to see you soon!\n" + line);

    }
}
