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

    private MemoryGameCard firstFlippedCard;
    private MemoryGameCard secondFlippedCard;

    private int guessesLabelValue;
    private int matchesLabelValue;



    @FXML
    void reset(ActionEvent event) {
        firstFlippedCard = null;
        secondFlippedCard = null;

        guessesLabelValue = 0;
        matchesLabelValue = 0;

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

        //Assign images and event listeners to each imageView in imageFlowPane
        IntStream.range(0, imageFlowPane.getChildren().size())
                .forEach(i -> {
                    MemoryGameCard displayedCard = memoryGameCards.get(i);
                    ImageView imageView = (ImageView) imageFlowPane.getChildren().get(i);
                    imageView.setImage(getCardBackImage());

                    imageView.setUserData(i);

                    //Set click event listener
                    imageView.setOnMouseClicked(mouseEvent -> onMemoryGameCardClicked(displayedCard, imageView));
                });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset(new ActionEvent());
    }

    Image getCardImage(Card card){
        return new Image(App.class.getResourceAsStream(String.format("images/%s_%s.png", card.getRank(), card.getSuit())));
    }

    Image getCardBackImage(){
        return new Image(App.class.getResourceAsStream("images/card_back_red.png"));
    }

    private void updateGuessesLabel(int value){
        guessesLabel.setText(Integer.toString(value));
    }

    private void updateMatchesLabel(int value){
        matchesLabel.setText(Integer.toString(value));
    }

    /**
     * Event handler that handles the logic of a mouse press on a non-matched dealt card.
     * @param clickedCard
     * @param clickedImageView
     */
    private void onMemoryGameCardClicked(MemoryGameCard clickedCard, ImageView clickedImageView){
        if (clickedCard.isMatched())
            return;

        if (firstFlippedCard == null)
            firstFlippedCard = clickedCard;
        else if (secondFlippedCard == null) {
            secondFlippedCard = clickedCard;
            findMatch();
        }
        else {
            showAllCardBacks();
            firstFlippedCard = clickedCard;
            secondFlippedCard = null;
        }

        clickedImageView.setImage(getCardImage(clickedCard));
    }

    /**
     * Checks for a match between first and second card selected.
     */
    private void findMatch(){
        if (firstFlippedCard.matches(secondFlippedCard)){
            updateMatchesLabel(++matchesLabelValue);
            firstFlippedCard.setMatched(true);
            secondFlippedCard.setMatched(true);
        }
        else{
            updateGuessesLabel(++guessesLabelValue);
            firstFlippedCard.setMatched(false);
            secondFlippedCard.setMatched(false);
        }
    }

    /**
     * Sets all unmatched cards back to their un-flipped image states (card backs).
     */
    private void showAllCardBacks(){
        imageFlowPane.getChildren().forEach(node ->{
            ImageView imageView = (ImageView) node;
            if (!memoryGameCards.get((int) imageView.getUserData()).isMatched())
                imageView.setImage(getCardBackImage());
        });
    }
}

