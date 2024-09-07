package memorycardgame.memorycardgame.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MemoryCardGameController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
