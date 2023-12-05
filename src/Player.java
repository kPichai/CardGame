// Kieran Pichai CardGame 2023 (CS2)
import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int points;

    public Player(String name) {
        this.name = name;
        points = 0;
        hand = new ArrayList<Card>();
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        points = 0;
        this.hand = hand;
    }

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

    public String toString() {
        String str = name + " has " + points + " points\n";
        str += name + "'s cards: ";
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