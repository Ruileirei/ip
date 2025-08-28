package Jerome.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String str = "[T][" + this.getStatus() + "] " + this.description;
        return str;
    }
}
