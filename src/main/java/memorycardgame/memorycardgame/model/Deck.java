package memorycardgame.memorycardgame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        List<String> ranks = Card.getValidRanks();
        List<String> suits = Card.getValidSuits();

        this.deck = new ArrayList<>(
                suits.stream()
                        .flatMap(suit -> ranks.stream()
                                .map(rank -> new Card(rank, suit))
                        )
                        .collect(Collectors.toList())
        );
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    @Override
    public String toString() {
        return deck.stream()
                .map(Card::toString)
                .collect(Collectors.joining("\n"));
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    /**
     * Pops the top card in the deck (index 0). This will remove said top card from the deck and return it.
     * If the deck is empty, this method will return null.
     * @return Card object or null.
     */
    public Card dealCard(){
        return deck.isEmpty() ? null : deck.removeFirst();
    }


}
