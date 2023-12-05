// Kieran Pichai CardGame 2023 (CS2)
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<Card>();
        for (int i = 0; i < suits.length; i++) {
            for (int k = 0; k < ranks.length; k++) {
                cards.add(new Card(suits[i], ranks[k], values[k]));
            }
        }
        shuffle();
        cardsLeft = cards.size();
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card deal() {
        if (isEmpty()) {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    public void shuffle() {
//      for (int i = cardsLeft - 1; i > -1; i++) {
//          int randIndex = (int)(Math.random()*i);
//          Card temp = cards.get(randIndex);
//          cards.set(randIndex, cards.get(i));
//          cards.set(i, temp);
//      }
        Collections.shuffle(cards);
    }
}