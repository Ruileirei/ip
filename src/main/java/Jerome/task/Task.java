package Jerome.task;

/**
 * Represents a task.
 * The base class for different types of tasks (Todo, Deadline, Event).
 */
public abstract class Task {
    protected String description;
    protected boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.completed = true;
        System.out.println("Nice! I have marked the following task as complete!");
        System.out.println(this);
    }

    /**
     * Unmarks the task as incomplete.
     */
    public void unmark() {
        this.completed = false;
        System.out.println("Aww! I will unmark the following task...");
        System.out.println(this);
    }

    /**
     * Returns true if the task is completed.
     */
    public boolean isDone() {
        return completed;
    }

    /**
     * Returns the description of the task.
     */

    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task (x for done, space for not done).
     */
    public String getStatus() {
        return (completed ? "x"
                           : " ");
    }

    /**
     * Returns a string representation of the task.
     */
    public abstract String toString();
}
