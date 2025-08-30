package Jerome.command;

import Jerome.TaskList;
import Jerome.ui.Ui;
import Jerome.storage.Storage;
import Jerome.util.JeromeException;

/**
 * Abstract class representing a command in the system.
 * All specific commands extend this class and implement the execute method.
 */
public abstract class Command {

    /**
     * Executes the command with the provided task list, UI, and storage.
     *
     * @param tasks The task list to be manipulated.
     * @param ui The UI component for user interaction.
     * @param storage The storage component to save data.
     * @throws JeromeException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JeromeException;

    /**
     * Determines if the command is an exit command.
     *
     * @return false by default. Specific commands may override this method.
     */

    public boolean isExit() {
        return false;
    }
}