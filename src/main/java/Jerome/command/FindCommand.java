package Jerome.command;

import Jerome.TaskList;
import Jerome.ui.Ui;
import Jerome.storage.Storage;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        boolean isFound = false;

        ui.lineText();
        ui.print("Here are the matching tasks in your list: ");

        for (int i = 0; i < tasks.size(); i++) {
            String desc = tasks.get(i).getDescription().toLowerCase();
            if (desc.contains(keyword)) {
                ui.print((i+1) + ". " + tasks.get(i));
                isFound = true;
            }
        }

        if (!isFound) {
            ui.print("No tasks match your search...");
        }

        ui.lineText();
    }
}
