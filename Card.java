public class Card{
    private final int rank;
    private final int suit;

    public static final String[] RANKS = {
            null, "Ace", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "Jack", "Queen", "King"};

    public static final String[] SUITS = {
            "Clubs", "Diamonds", "Hearts", "Spades"};

    public Card(int rank, int suit){
        this.rank = rank;
        this.suit = suit;
    }

    public String toString() {
        return RANKS[this.rank] + " of " + SUITS[this.suit];
    }

    public int compareTo(Card that) {
        if (this.suit < that.suit) {
            return -1;
        }
        if (this.suit > that.suit) {
            return 1;
        }

        int thisRank = (this.rank == 1) ? 14 : this.rank;
        int thatRank = (that.rank == 1) ? 14 : that.rank;

        if (thisRank < thatRank) {
            return -1;
        }
        if (thisRank > thatRank) {
            return 1;
        }

        return 0;
    }

    public int getRank() {
        return this.rank;
    }

    public int getSuit() {
        return this.suit;
    }
}