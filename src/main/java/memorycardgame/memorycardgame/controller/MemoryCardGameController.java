package memorycardgame.memorycardgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import memorycardgame.memorycardgame.App;
import memorycardgame.memorycardgame.model.Card;

import java.io.IOException;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class MemoryCardGameController implements Initializable {

    @FXML
    private ImageView SampleImage;

    @FXML
    private Label guessesLabel;

    @FXML
    private Label matchesLabel;

    @FXML
    private Button resetButton;

    @FXML
    void reset(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(App.class.getResourceAsStream("images/2_spades.png"));
        SampleImage.setImage(image);


    }
}

