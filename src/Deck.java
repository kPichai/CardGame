// Kieran Pichai CardGame 2023 (CS2)
import java.util.ArrayList;
import java.lang.Math;

// This class manages the overall card deck which is what both individual player hands are made out off
public class Deck {
    // instance variables that keep track of the hand and simply how many cards are left in it
    private ArrayList<Card> cards;
    private int cardsLeft;

    // constructor that initializes all types of cards
    public Deck(String[] ranks, String[] suits, int[] values) {
        // initializes the deck of cards
        cards = new ArrayList<Card>();
        // fills the deck with all possible types of cards
        for (int i = 0; i < suits.length; i++) {
            for (int k = 0; k < ranks.length; k++) {
                cards.add(new Card(suits[i], ranks[k], values[k]));
            }
        }
        cardsLeft = cards.size();
        // shuffle the deck such that when it is distributed / dealt the cards arent in order and are random
        shuffle();
    }

    // returns true if the deck is empty or false if otherwise
    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    // getters for both instance variables below
    public int getCardsLeft() {
        return cardsLeft;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    // deals one card at a time
    public Card deal() {
        if (isEmpty()) {
            return null;
        }
        // decrements cardsLeft so that it know which card to return next
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    // Shuffles the deck randomly (called in constructor to make sure it is truly a random deck)
    public void shuffle() {
        // loops through the deck backwards and switches the current card and any random card
        for (int i = cardsLeft - 1; i > -1; i--) {
            int randIndex = (int)(Math.random()*i);
            // switches order or cards in order to randomize the deck
            Card temp = cards.get(randIndex);
            cards.set(randIndex, cards.get(i));
            cards.set(i, temp);
        }
    }
}