import java.sql.SQLOutput;
import java.util.ArrayList;
// Kieran Pichai CardGame 2023 (CS2)
public class CardGame {
    final private String[] suits = {"Clubs", "Spades", "Hearts", "Diamonds"};
    final private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    final private int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private Player p1;
    private Player p2;
    private Deck deck;
    // Go Fish
    public CardGame() {
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        deck = new Deck(ranks, suits, values);
        for (int i = 0; i < 7; i++) {
            p1.addCard(deck.deal());
            p2.addCard(deck.deal());
        }
        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }
    public void checkBooks() {
        for (int i = 0; i < 13; i++) {
            int instances = 0;
            for (int k = 0; k < p1.getHand().size(); k++) {
                if (p1.getHand().get(k).getPoint() == i) {
                    instances++;
                }
            }
            if (instances == 4) {
                p1.addPoints(1);
            }
        }

        for (int i = 0; i < 13; i++) {
            int instances = 0;
            for (int k = 0; k < p2.getHand().size(); k++) {
                if (p2.getHand().get(k).getPoint() == i) {
                    instances++;
                }
            }
            if (instances == 4) {
                p2.addPoints(1);
            }
        }
    }

    public void printInstructions() {

    }

    public boolean checkGameEnd() {
        return (deck.isEmpty()) || (p1.getPoints() > 6) || (p2.getPoints() > 6);
    }
    public void runGame() {
        printInstructions();

    }
    public static void main(String[] args) {
        CardGame c1 = new CardGame();
        c1.runGame();
    }
}