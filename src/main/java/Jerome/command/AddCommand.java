package Jerome.command;

import Jerome.TaskList;
import Jerome.storage.Storage;
import Jerome.task.Task;
import Jerome.ui.Ui;



/**
 * Command to add a task to the task list.
 * This command adds the specified task to the list and then displays
 * a success message while saving the updated task list to storage.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add task command.
     *
     * @param tasks The task list to which the task is added.
     * @param ui The UI component for displaying success messages.
     * @param storage The storage component for saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.successfulAddText(task, tasks.size());
        storage.save(tasks);
    }
}
