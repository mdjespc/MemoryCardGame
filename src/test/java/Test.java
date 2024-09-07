import memorycardgame.memorycardgame.model.Card;
import memorycardgame.memorycardgame.model.Deck;

public class Test {
    public static void main(String[] args) {
        Card AceOfSpades = new Card("aCe", "Spades");
        Card KingOfHearts = new Card("King", "hearts");

        System.out.println(AceOfSpades);
        System.out.printf("Rank: %d\nColor: %s %n", AceOfSpades.getValue(), AceOfSpades.getColor() );
        System.out.println();

        System.out.println(KingOfHearts);
        System.out.printf("Rank: %d\nColor: %s %n", KingOfHearts.getValue(), KingOfHearts.getColor() );
        System.out.println();


        System.out.println("Creating new deck...");
        Deck TestDeck = new Deck();
        System.out.println(TestDeck);
        System.out.println();


        System.out.println("Shuffling new deck...");
        TestDeck.shuffleDeck();
        System.out.println(TestDeck);
        System.out.println();

        System.out.println("Dealing top 8 cards:");
        for (int i = 0; i < 8; i++)
            System.out.println(TestDeck.dealCard());
        System.out.println();

    }
}