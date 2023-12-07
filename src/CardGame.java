import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println("\nWelcome to Go Fish! Below are the rules:");
        System.out.println("\n- You will play with a friend and alternate turns");
        System.out.println("- On your turn you are allowed to ask for a card from another player (only a card you already have at least one instance of)");
        System.out.println("    - If they have that card in their hand they must give you all instances of it, if you guess correctly you also get to keep your turn");
        System.out.println("    - Otherwise you draw a new card from the deck and pass your turn on");
        System.out.println("- Everytime you get a set of 4 same cards you place them down and call it a book");
        System.out.println("    - The game ends once a player gets 7 or more books");
        System.out.println("\nI hope you have fun playing and good luck!");
        System.out.println("\n\nNow let the game begin: \n");
    }

    public Player checkGameEnd() {
        checkBooks();
        if (p1.getPoints() > 6) {
            return p1;
        } else if (p2.getPoints() > 6) {
            return p2;
        }
        return null;
    }

    public boolean checkValidTurn(String cardWanted, int turn) {
        if (turn == 0) {
            for (int i = 0; i < p1.getHand().size(); i++) {
                if (p1.getHand().get(i).getRank().equals(cardWanted)) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < p2.getHand().size(); i++) {
                if (p2.getHand().get(i).getRank().equals(cardWanted)) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Integer> checkInstances(String nameOfCard, ArrayList<Card> c1) {
        ArrayList<Integer> removed = new ArrayList<Integer>();
        for (int i = p2.getHand().size() - 1; i >= 0 ; i--) {
            if (c1.get(i).getRank().equals(nameOfCard)) {
                removed.add(i);
            }
        }
        return removed;
    }

    public void runGame() {
        Scanner sc = new Scanner(System.in);
        printInstructions();
        String response;
        int turn = 0;
        Player possibleWinner = null;
        while (possibleWinner ==  null) {
            if (turn == 0) {
                System.out.println(p1.toString());
                do {
                    System.out.println("Player 1: What card would you like to fish for? (Your answer can be 2-10 or Jack, Queen, King, Ace and must also be a card currently in your hand)");
                    response = sc.nextLine();
                } while (!checkValidTurn(response, turn));
                ArrayList<Integer> toRemove = checkInstances(response, p2.getHand());
                if (toRemove.isEmpty()) {
                    turn = 1;
                    p1.addCard(deck.deal());
                    System.out.println("Whoops, you have to Go Fish!");
                    System.out.println("You drew a " + p1.getHand().get(p1.getHand().size() - 1) + ".");
                } else {
                    for (int i = 0; i < toRemove.size(); i++) {
                        p1.addCard(p2.getHand().get(toRemove.get(i)));
                    }
                    for (int i = 0; i < toRemove.size(); i++) {
                        p2.getHand().remove(toRemove.get(i));
                    }
                    System.out.println("Nice job! You gained " + toRemove.size() + " " + response + "'s.");
                    System.out.println("You get another turn now!");
                }
            }
            System.out.flush();
            if (turn == 1) {
                System.out.println(p2.toString());
                do {
                    System.out.println("Player 2: What card would you like to fish for? (Your answer can be 2-10 or Jack, Queen, King, Ace and must also be a card currently in your hand)");
                    response = sc.nextLine();
                } while (!checkValidTurn(response, turn));
                ArrayList<Integer> toRemove = checkInstances(response, p1.getHand());
                if (toRemove.isEmpty()) {
                    turn = 0;
                    p2.addCard(deck.deal());
                    System.out.println("Whoops, you have to Go Fish!");
                    System.out.println("You drew a " + p2.getHand().get(p2.getHand().size() - 1) + ".");
                } else {
                    for (int i = 0; i < toRemove.size(); i++) {
                        p2.addCard(p1.getHand().get(toRemove.get(i)));
                    }
                    for (int i = 0; i < toRemove.size(); i++) {
                        p1.getHand().remove(toRemove.get(i));
                    }
                    System.out.println("Nice job! You gained " + toRemove.size() + " " + response + "'s.");
                    System.out.println("You get another turn now!");
                }
            }
            System.out.flush();
            possibleWinner = checkGameEnd();
        }
        System.out.println("Congrats on winning " + checkGameEnd().getName() + "!");
        System.out.println(checkGameEnd().toString());
    }
    public static void main(String[] args) {
        CardGame c1 = new CardGame();
        c1.runGame();
    }
}