// Kieran Pichai CardGame 2023 (CS2)
// This class manages the specific values of each individual card
public class Card {
    // instance variables to distinguish each type of card
    private String suit;
    private String rank;
    private int point;


    // Constructor to create an object with correct paramaters of a certain card
    public Card(String suit, String rank, int point) {
        this.suit = suit;
        this.rank = rank;
        this.point = point;
    }

    // Below here are getter and setter methods for all the various instance variables
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

    // returns a formatted string of the information regarding the given card
    public String toString() {
        return rank + " of " + suit;
    }
}