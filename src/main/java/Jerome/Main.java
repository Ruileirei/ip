package Jerome;

import Jerome.gui.DialogBox;
import Jerome.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX entry point for Jerome chatbot GUI.
 */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "data/jerome.txt";
    private final Image userImage = loadImage("/images/MonkeSponge.jpg");
    private final Image JeromeImage = loadImage("/images/funnyMonke.png");
    private final String filePath;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Jerome jerome = new Jerome();
    /**
     * Constructs a Main instance with a custom file path.
     *
     * @param filePath The path to load/save tasks from.
     */
    public Main(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Constructs a Main instance using the default file path.
     */
    public Main() {
        this(DEFAULT_FILE_PATH);
    }

    private Image loadImage(String path) {
        var stream = getClass().getResourceAsStream(path);
        if (stream == null) {
            System.out.println("Image not found: " + path);
            return new Image("https://via.placeholder.com/50"); // fallback
        }
        return new Image(stream);
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        if (userText == null || userText.isBlank()) {
            return;
        }
        String response = jerome.getResponse(userText);
        addDialogs(dialogContainer,
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(response, JeromeImage)
        );

        userInput.clear();

        // Auto-scroll to the latest message
        scrollPane.setVvalue(1.0);

        // If the user typed "bye", exit the application
        if (response.contains("Bye")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> Platform.exit()); // Exit the app after 2 seconds
            pause.play();
        }
    }

    public static void addDialogs(VBox container, DialogBox... boxes) {
        container.getChildren().addAll(boxes);
    }


    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Ui.welcomeText(), JeromeImage)
        );

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle("Jerome");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

    }
}
