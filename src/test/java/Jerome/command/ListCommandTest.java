package Jerome.command;

import Jerome.TaskList;
import Jerome.task.Todo;
import Jerome.ui.Ui;
import Jerome.storage.Storage;
import org.junit.jupiter.api.Test;

public class ListCommandTest {

    @Test
    void testExecute() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));

        Ui ui = new Ui();
        Storage storage = new Storage();

        ListCommand command = new ListCommand();

        command.execute(tasks, ui, storage);
    }
}
