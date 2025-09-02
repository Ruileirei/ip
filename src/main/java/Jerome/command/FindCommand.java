package Jerome.command;

import Jerome.TaskList;
import Jerome.storage.Storage;
import Jerome.ui.Ui;


/**
 * Command to search for tasks by keyword.
 * This command filters and displays tasks whose descriptions contain the given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by printing matching tasks to the UI.
     *
     * @param tasks The task list to search within.
     * @param ui The UI component to display results.
     * @param storage The storage component (not used here).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        boolean isFound = false;

        ui.lineText();
        ui.print("Here are the matching tasks in your list: ");

        for (int i = 0; i < tasks.size(); i++) {
            String desc = tasks.get(i).getDescription().toLowerCase();
            if (desc.contains(keyword)) {
                ui.print((i + 1) + ". " + tasks.get(i));
                isFound = true;
            }
        }

        if (!isFound) {
            ui.print("No tasks match your search...");
        }

        ui.lineText();
    }
}
