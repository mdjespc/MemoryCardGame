package memorycardgame.memorycardgame.model;

public class MemoryGameCard extends Card {
    private boolean isMatched;

    public MemoryGameCard(String rank, String suit) {
        super(rank, suit);
        this.isMatched = false;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    /**
     * Compares this instance's rank and suit to that of another Card instance's.
     * If both rank and suit values match, then both cards are considered to be equal.
     * @param otherCard Card to compare values with.
     * @return true or false
     */
    public boolean matches(MemoryGameCard otherCard) {
        return (this.getRank().equals(otherCard.getRank()) &&
                this.getSuit().equals(otherCard.getSuit()));
    }
}