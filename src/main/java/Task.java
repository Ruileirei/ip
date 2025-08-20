public abstract class Task {
    protected String description;
    protected boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public void mark() {
        this.completed = true;
        System.out.println("Nice! I have marked the following task as complete!");
        System.out.println(this);
    }

    public void unmark() {
        this.completed = false;
        System.out.println("Aww! I will unmark the following task...");
        System.out.println(this);
    }

    public String getStatus() {
        return (completed ? "x"
                           : " ");
    }

    public abstract String toString();
}
