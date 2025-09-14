package jerome.command;

import jerome.TaskList;
import jerome.storage.Storage;
import jerome.ui.Ui;


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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        boolean isFound = false;
        for (int i = 0; i < tasks.size(); i++) {
            String desc = tasks.get(i).getDescription().toLowerCase();
            if (desc.contains(keyword)) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
                isFound = true;
            }
        }
        if (!isFound) {
            sb.append("No tasks match your search...\n");
        }
        return sb.toString();
    }
}
