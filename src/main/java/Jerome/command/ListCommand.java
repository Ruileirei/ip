package Jerome.command;

import Jerome.TaskList;
import Jerome.storage.Storage;
import Jerome.ui.Ui;


/**
 * Command to list all tasks in the task list.
 * This command prints out all tasks to the UI.
 */
public class ListCommand extends Command {

    /**
     * Executes the list task command.
     *
     * @param tasks The task list to be printed.
     * @param ui The UI component to display the task list.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.taskListText(tasks.getAll());
    }
}
