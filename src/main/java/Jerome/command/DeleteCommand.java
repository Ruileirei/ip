package Jerome.command;

import Jerome.task.Task;
import Jerome.TaskList;
import Jerome.ui.Ui;
import Jerome.storage.Storage;
import Jerome.util.JeromeException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeromeException {
        if (index < 0 || index >= tasks.size()) {
            throw new JeromeException("Invalid task index for deletion.");
        }
        Task removed = tasks.remove(index);
        ui.successfulDeleteText(removed, tasks.size());
        storage.save(tasks);
    }
}
