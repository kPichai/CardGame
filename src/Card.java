// Kieran Pichai CardGame 2023 (CS2)
public class Card {
    private String suit;
    private String rank;
    private int point;

    public Card(String suit, String rank, int point) {
        this.suit = suit;
        this.rank = rank;
        this.point = point;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getPoint() {
        return point;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String toString() {
        return rank + " of " + suit;
    }
}
