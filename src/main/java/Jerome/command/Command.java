package Jerome.command;

import Jerome.TaskList;
import Jerome.ui.Ui;
import Jerome.storage.Storage;
import Jerome.util.JeromeException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JeromeException;

    public boolean isExit() {
        return false;
    }
}
