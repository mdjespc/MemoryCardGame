package memorycardgame.memorycardgame.model;

import java.util.Arrays;
import java.util.List;

public class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        setRank(rank);
        setSuit(suit);
    }

    public String getRank() {
        return rank;
    }

    public static List<String> getValidRanks(){
        return Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace");
    }

    /**
     * Sets the value of a Card object's rank.
     * @param rank String.
     *             Valid ranks are: "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"
     */
    public void setRank(String rank) {
        rank = rank.toLowerCase();
        if (getValidRanks().contains(rank))
            this.rank = rank;
        else
            throw new IllegalArgumentException(rank + " is an invalid suit. Must be one of " + getValidRanks());
    }


    public String getSuit() {
        return suit;
    }

    public static List<String> getValidSuits(){
        return Arrays.asList("hearts", "diamonds", "clubs", "spades");
    }
    /**
     * Sets the value of a Card object's suit.
     * @param suit String.
     *             Valid suits are: "hearts", "diamonds", "clubs", "spades"
     */
    public void setSuit(String suit) {
        suit = suit.toLowerCase();
        if (getValidSuits().contains(suit))
            this.suit = suit;
        else
            throw new IllegalArgumentException(suit + " is an invalid suit. Must be one of " + getValidSuits());
    }

    @Override
    public String toString(){
        return rank + " of " + suit;
    }

    public String getColor(){
        return suit.equals("hearts") || suit.equals("diamonds") ?  "red" : "black";
    }

    public int getValue(){
        return getValidRanks().indexOf(rank) + 2;
    }
}