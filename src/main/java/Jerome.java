import java.util.Scanner;

public class Jerome {
    public static void main(String[] args) {
        String line = "_____________________________";
        Scanner sc = new Scanner(System.in);
        //introduction
        System.out.println(line + "\nHello, I'm Jerome!\n" +  "What can I do for you?\n" + line);

        //user input
        String response = sc.next();
        while (!response.equals("bye")) {
            System.out.println(line + "\n" +response + "\n" + line);
            response = sc.next();
        }

        //ending
        System.out.println("Bye. Hope to see you soon!\n" + line);

    }
}
