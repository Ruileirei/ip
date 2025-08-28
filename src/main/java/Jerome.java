public class Jerome {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Jerome() {
        this.storage = new Storage();
        this.ui = new Ui();
        storage.makeExist();
        this.tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.welcomeText();
        boolean isExit = false;

        while (!isExit) {
            try {
                String response = ui.readCommand();
                Command c = Parser.parse(response);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JeromeException e) {
                ui.showError((e.getMessage()));
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new Jerome().run();
    }
}
