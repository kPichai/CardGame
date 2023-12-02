import java.util.ArrayList;
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

    public Card deal() {
        if (isEmpty()) {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
