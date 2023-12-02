import java.util.ArrayList;

public class CardGame {
    final private String[] suits = {"Clubs", "Spades", "Hearts", "Diamonds"};
    final private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    final private int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private int books;
    private Player p1;
    private Player p2;
    private Deck deck;
    // Go Fish
    public CardGame() {
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        deck = new Deck(ranks, suits, values);
        books = 0;
        for (int i = 0; i < 7; i++) {
            p1.addCard(deck.deal());
            p2.addCard(deck.deal());
        }
    }

    public void printInstructions() {
        // ADD HERE
    }
    public static void main(String[] args) {
        CardGame c1 = new CardGame();
        // ADD HERE TO PLAY GAME
    }
}