package Jerome.command;

import Jerome.task.Task;
import Jerome.TaskList;
import Jerome.ui.Ui;
import Jerome.storage.Storage;
import Jerome.util.JeromeException;

/**
 * Command to delete a task from the task list.
 * This command removes a task at a specified index and updates the task list
 * and storage accordingly. It throws an exception if the index is invalid.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete task command.
     *
     * @param tasks The task list from which the task is deleted.
     * @param ui The UI component for displaying success or error messages.
     * @param storage The storage component for saving the updated task list.
     * @throws JeromeException If the index is invalid for deletion.
     */
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

