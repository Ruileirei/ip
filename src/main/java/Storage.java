import java.nio.charset.StandardCharsets;
import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class Storage {
    private static final String div = " | ";
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
                String[] substr = line.split("\\s\\|\\s");
                if (substr.length < 3) {
                    System.out.println("Warning: Skipping corrupted line " + lineNum + " (Too few fields).");
                    continue;
                }
                String type = substr[0].trim();
                String status = substr[1].trim();
                String desc = substr[2].trim();
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
                            if (substr.length < 4) {
                                System.out.println("Warning: Skipping corrupted line " + lineNum + " (Missing deadline '/by').");
                                continue;
                            }
                            String by = substr[3].trim();
                            task = new Deadline(desc, by);
                            break;
                        case "E":
                            if (substr.length < 5) {
                                System.out.println("Warning: Skipping corrupted line " + lineNum + " (Missing event '/from' or '/to').");
                                continue;
                            }
                            String from = substr[3].trim();
                            String to = substr[4].trim();
                            task = new Event(desc, from, to);
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

    public void save(List<Task> tasks) {
        List<String> output = new ArrayList<>();
        for (Task t : tasks) {
            output.add(formatting(t));
        }
        try {
            Files.write(loc, output, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Warning: Could not save tasks to " + loc + ".");
        }
    }

    private static String formatting(Task t) {
        int status = t.isDone() ? 1 : 0;
        if (t instanceof Todo) {
            return "T" + div + status + div + ((Todo) t).getDescription();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D" + div + status + div + d.getDescription() + div + d.getBy();
        } else if (t instanceof Event) {
            Event eve = (Event) t;
            return "E" + div + status + div + eve.getDescription() + div + eve.getFrom() + div + eve.getTo();
        } else
            return "T" + div + status + div + t.toString();
    }
}
