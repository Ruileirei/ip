package Jerome.command;

import Jerome.TaskList;
import Jerome.ui.Ui;
import Jerome.storage.Storage;
import Jerome.task.Task;
import Jerome.task.Todo
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {

    @Test
    void testExecuteAddsTask() {
        Task task = new Todo("Test Task");
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        AddCommand command = new AddCommand(task);

        command.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getDescription());
    }
}
