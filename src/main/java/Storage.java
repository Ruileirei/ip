import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * Encapsulates loading/saving tasks to and from Jerome.txt
 * Format:
 *  T | 1/0 | description
 *  D | 1/0 | description | by
 *  E | 1/0 | description | from | to
 *
 */

public class Storage {
    private static final String DIV = " | ";
    private final Path loc;

    public Storage() {
        this(Paths.get("data", "Jerome.txt"));
    }

    public Storage(Path loc) {
        this.loc = loc;
    }

    public void makeExist() {
        try {
            if (loc.getParent() != null) {
                Files.createDirectories((loc.getParent()));
            }
            if (!Files.exists((loc))) {
                Files.write(loc, new byte[0]);
            }
        } catch (IOException e) {
            System.out.println("Warning, path: " + loc + " not found. Changes cannot be saved.");
        }
    }

    /**
     * Reads Jerome.txt (if it exists) and adds the saved tasks into the list when Jerome.java is run
     *
     */

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(loc)) return tasks;

        try {
            List<String> cont = Files.readAllLines(loc, StandardCharsets.UTF_8);
            int lineNum = 0;
            for (String line: cont) {
                lineNum++;
                if (line.isEmpty()) {
                    continue;
                }
                String[] subStr = line.split("\\s\\|\\s");
                if (subStr.length < 3) {
                    System.out.println("Warning: Skipping corrupted line " + lineNum + " (Too few fields).");
                    continue;
                }
                String type = subStr[0].trim();
                String status = subStr[1].trim();
                String desc = subStr[2].trim();
                boolean bool;

                if (status.equals("1")) {
                    bool = true;
                } else if (status.equals("0")) {
                    bool = false;
                } else {
                    System.out.println("Warning: Skipping corrupted line " + lineNum + " (Invalid Status).");
                    continue;
                }

                try {
                    Task task;
                    switch(type) {
                        case "T":
                            task = new Todo(desc);
                            break;
                        case "D":
                            if (subStr.length < 4) {
                                System.out.println("Warning: Skipping corrupted line " + lineNum + " (Missing deadline '/by').");
                                continue;
                            }
                            try {
                                String by = subStr[3].trim();
                                task = new Deadline(desc, by);
                            } catch (DateTimeParseException e) {
                                System.out.println("Warning: Skipping line " + lineNum + " (Invalid datetime format).");
                                continue;
                            }
                            break;
                        case "E":
                            if (subStr.length < 5) {
                                System.out.println("Warning: Skipping corrupted line " + lineNum + " (Missing event '/from' or '/to').");
                                continue;
                            }
                            try {
                                String from = subStr[3].trim();
                                String to = subStr[4].trim();
                                task = new Event(desc, from, to);
                            } catch (DateTimeParseException e) {
                                System.out.println("Warning: Skipping line " + lineNum + " (Invalid datetime format).");
                                continue;
                            }
                            break;
                        default:
                            System.out.println("Warning: Skipping corrupted line " + lineNum + " (Unknown type: " + type + ").");
                            continue;
                    }
                    if (bool) task.mark();
                    tasks.add(task);
                } catch (Exception e1) {
                    System.out.println("Warning: Skipping corrupted line " + lineNum + " (Could not construct task: " + e1.getMessage() + ").");
                }
            }

        } catch (IOException e) {
            System.out.println("Warning: Could not load tasks from " + loc + ". Starting fresh.");
        }
        return tasks;
    }

    /**
     * Formats every task in a List of tasks, and adds them into Jerome.txt
     *
     */

    public void save(TaskList tasks) {
        List<String> output = new ArrayList<>();
        List<Task> ts = tasks.getAll();
        for (Task t : ts) {
            output.add(formatting(t));
        }
        try {
            Files.write(loc, output, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Warning: Could not save tasks to " + loc + ".");
        }
    }

    /**
     * Formats a task into a single string before being added to Jerome.txt
     * Format:
     *  T | 1/0 | description
     *  D | 1/0 | description | by
     *  E | 1/0 | description | from | to
     *
     */

    private static String formatting(Task t) {
        int status = t.isDone() ? 1 : 0;
        if (t instanceof Todo) {
            return "T" + DIV + status + DIV + t.getDescription();
        } else if (t instanceof Deadline d) {
            return "D" + DIV + status + DIV + d.getDescription() + DIV + d.getByRaw();
        } else if (t instanceof Event eve) {
            return "E" + DIV + status + DIV + eve.getDescription() + DIV + eve.getFromRaw() + DIV + eve.getToRaw();
        } else
            return "T" + DIV + status + DIV + t.toString();
    }
}
