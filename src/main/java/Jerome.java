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
            } else if (response.equals("list")) {
                //if task list is empty
                if (ls.isEmpty()) {
                    System.out.println("There is no tasks currently :/");
                } else {
                    //print all tasks if list is not empty
                    System.out.println("Here are your tasks: ");
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
                    //if input is mark x where x is not an integer.
                    System.out.println("Sorry! I can't find that task :(");
                }
            } else {
                try {
                    //process input
                    String[] formattedInput = response.split("/", 2);
                    String[] typeAndTask = formattedInput[0].split(" ", 2);
                    String taskType = typeAndTask[0];
                    if (typeAndTask.length == 1) {
                        if (!taskType.equals("todo") && !taskType.equals("deadline") && !taskType.equals("event")) {
                            throw new JeromeException.InvalidTaskTypeException();
                        }
                    }
                    String desc = typeAndTask[1];

                    if (taskType.equals("todo")) {
                        if (response.equals("todo")) {
                            throw new JeromeException.EmptyTaskException("todo");
                        }
                        if (response.contains("/from ") || response.contains("/to ") || response.contains("/by")) {
                            throw new JeromeException.WrongfulArgumentException("todo",
                                    response.contains("/from") ? "from" : null,
                                    response.contains("/to") ? "to" : null,
                                    response.contains("/by") ? "by" : null
                            );
                        }
                        Task task = new Todo(desc);
                        ls.add(task);
                        System.out.println(line + "\n" + "Got it! I added:\n" + task + "\n" +
                                "There are now " + ls.size() + " tasks!\n" + line);

                    } else if (taskType.equals("event")) {
                        if (response.equals("event")) {
                            throw new JeromeException.EmptyTaskException("event");
                        }
                        if (formattedInput.length == 1) {
                            throw new JeromeException.InvalidTaskDeclarationException("event");
                        } else {
                            if (response.contains("/by")) {
                                throw new JeromeException.WrongfulArgumentException("event", null, null, "by");
                            }
                            if (!response.contains("/from") || !response.contains("/to")) {
                                throw new JeromeException.InvalidTaskDeclarationException("event");
                            }
                            String[] timeFrame = formattedInput[1].split("/");
                            String from = timeFrame[0].substring(5);
                            String to = timeFrame[1].substring(3);
                            Task task = new Event(desc, from, to);
                            ls.add(task);
                            System.out.println(line + "\n" + "Got it! I added:\n" + task + "\n" +
                                    "There are now " + ls.size() + " tasks!\n" + line);
                        }

                    } else if (taskType.equals("deadline")) {
                        if (response.equals("deadline")) {
                            throw new JeromeException.EmptyTaskException("deadline");
                        }
                        if (formattedInput.length == 1) {
                            throw new JeromeException.InvalidTaskDeclarationException("deadline");
                        }
                        if (response.contains("/from") || response.contains("/to")) {
                            throw new JeromeException.WrongfulArgumentException("deadline",
                                    response.contains("/from") ? "from" : null,
                                    response.contains("/to") ? "to" : null,
                                    null
                            );
                        }
                        if (!response.contains("/by")) {
                            throw new JeromeException.InvalidTaskDeclarationException("deadline");
                        }
                        String by = formattedInput[1].substring(3);
                        Task task = new Deadline(desc, by);
                        ls.add(task);
                        System.out.println(line + "\n" + "Got it! I added:\n" + task + "\n" +
                                "There are now " + ls.size() + " tasks!\n" + line);

                    } else {
                        throw new JeromeException.InvalidTaskTypeException();
                    }
                } catch (JeromeException.EmptyTaskException e1) {
                    System.out.println(line +  "\n"
                            + "My bad brother/sister but the description of " + e1.type
                            + " cannot be empty!" + "\n" +line);
                } catch (JeromeException.InvalidTaskTypeException e2) {
                    System.out.println(line + "\n"
                            + "Oh deary me... I'm sorry, but I don't know what task this is :-(" + "\n" + line);
                } catch (JeromeException.InvalidTaskDeclarationException e3) {
                    System.out.println(line + "\n"
                            + "Im soooo Sorry... " + e3.type
                            + " is missing fields..." + "\n" + line);
                } catch (JeromeException.WrongfulArgumentException e4) {
                    System.out.println(line + "\n" + e4.getMessage() + "\n" + line);
                }
            }
        }
        //ending
        System.out.println("Bye. Hope to see you soon!\n" + line);

    }
}
