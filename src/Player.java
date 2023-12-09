// Kieran Pichai CardGame 2023 (CS2)
import java.util.ArrayList;

// This class manages each individual player (p1 and p2 in the CardGame class)
public class Player {
    // Instance variables unique to each player
    private String name;
    private ArrayList<Card> hand;
    private int points;

    // Constructor that makes a player an empty hand and given name
    public Player(String name) {
        this.name = name;
        points = 0;
        hand = new ArrayList<Card>();
    }

    // Constructor that makes a player a given hand and given name
    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        points = 0;
        this.hand = hand;
    }

    // Getter and setter methods below for all instance variables
    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void addCard(Card c1) {
        hand.add(c1);
    }

    // Returns out a formatted string of the hand the player has
    public String toString() {
        String str = name + " has " + points + " points\n";
        str += name + "'s cards: ";
        // loops through the hand in order to print it out
        for (int i = 0; i < hand.size(); i++) {
            if (i == hand.size() - 1) {
                str += hand.get(i).toString() + ".";
                continue;
            }
            str += hand.get(i).toString() + ", ";
        }
        return str;
    }
}