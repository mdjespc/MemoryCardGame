package memorycardgame.memorycardgame.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import memorycardgame.memorycardgame.App;
import memorycardgame.memorycardgame.model.Deck;
import memorycardgame.memorycardgame.model.Card;
import memorycardgame.memorycardgame.model.MemoryGameCard;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class MemoryCardGameController implements Initializable {

    @FXML
    private FlowPane imageFlowPane;

    @FXML
    private Label guessesLabel;

    @FXML
    private Label matchesLabel;

    @FXML
    private Button resetButton;

    private ArrayList<MemoryGameCard> memoryGameCards;

    @FXML
    void reset(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        memoryGameCards = new ArrayList<>();
        Deck deck = new Deck();
        deck.shuffleDeck();

        for (int i = 0; i < imageFlowPane.getChildren().size() / 2; i++){
            //Add two copies of the dealt card to list
            Card cardDealt = deck.dealCard();
            MemoryGameCard  memoryGameCardDealt = new MemoryGameCard(cardDealt.getRank(), cardDealt.getSuit());
            memoryGameCards.add(memoryGameCardDealt);
            memoryGameCards.add(memoryGameCardDealt);
        }
        Collections.shuffle(memoryGameCards);

        for (int i = 0; i < imageFlowPane.getChildren().size(); i++){
            MemoryGameCard displayedCard = memoryGameCards.get(i);
            String imagePath = String.format("images/%s_%s.png", displayedCard.getRank(), displayedCard.getSuit());
            ImageView imageView = (ImageView) imageFlowPane.getChildren().get(i);
            imageView.setImage(new Image(App.class.getResourceAsStream(imagePath)));
        }

    }
}

