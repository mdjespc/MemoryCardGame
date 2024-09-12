package memorycardgame.memorycardgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MemoryCardGameController implements Initializable {

    @FXML
    private FlowPane imageFlowPane;

    @FXML
    private Label guessesLabel;

    @FXML
    private Label matchesLabel;

    @FXML
    private Button resetButton;

    private List<MemoryGameCard> memoryGameCards;

    @FXML
    void reset(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Deck deck = new Deck();
        deck.shuffleDeck();

        //Populate the memoryGameCards list by adding two copies of each dealt card
        memoryGameCards = IntStream.range(0, imageFlowPane.getChildren().size() / 2)
                .mapToObj(i -> {
                    Card dealtCard = deck.dealCard();
                    MemoryGameCard memoryGameCard = new MemoryGameCard(dealtCard.getRank(), dealtCard.getSuit());
                    return List.of(memoryGameCard, memoryGameCard);
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());

        //Shuffle the memoryGameCards list
        Collections.shuffle(memoryGameCards);

        //Assign images to each imageView in imageFlowPane
        IntStream.range(0, imageFlowPane.getChildren().size())
                .forEach(i -> {
                    MemoryGameCard displayedCard = memoryGameCards.get(i);
                    String imagePath = String.format("images/%s_%s.png", displayedCard.getRank(), displayedCard.getSuit());
                    ImageView imageView = (ImageView) imageFlowPane.getChildren().get(i);
                    imageView.setImage(new Image(App.class.getResourceAsStream(imagePath)));
                });
    }
}

