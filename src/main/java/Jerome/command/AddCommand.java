package Jerome.command;

import Jerome.task.Task;
import Jerome.TaskList;
import Jerome.ui.Ui;
import Jerome.storage.Storage;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.successfulAddText(task, tasks.size());
        storage.save(tasks);
    }
}
