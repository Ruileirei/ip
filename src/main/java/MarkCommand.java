public class MarkCommand extends Command {
    private final int index;
    private final boolean isUnmark;

    public MarkCommand(int index, boolean isUnmark) {
        this.index = index;
        this.isUnmark = isUnmark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        if (isUnmark) {
            task.unmark();
        } else {
            task.mark();
        }
        storage.save(tasks);
    }
}
