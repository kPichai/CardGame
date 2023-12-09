// Kieran Pichai CardGame 2023 (CS2)
// Importing classes necessary
import java.util.ArrayList;
import java.util.Scanner;

// Main go fish class that contains all of game logic, everything else about cards and players is abstracted into other classes
public class CardGame {
    // Instance variables, final ones are set values that stay constant between different games
    final private String[] suits = {"Clubs", "Spades", "Hearts", "Diamonds"};
    final private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    final private int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private Player p1;
    private Player p2;
    private Deck deck;
    // Go Fish constructor that makes two players and then initiates the game
    public CardGame() {
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        deck = new Deck(ranks, suits, values);
        for (int i = 0; i < 7; i++) {
            p1.addCard(deck.deal());
            p2.addCard(deck.deal());
        }
    }
    // Checks the amount of sets (books) of 4 cards a player has, which goes towards their total points
    public void checkBooks() {
        // Checks books found within player 1's hand
        for (int i = 0; i < 13; i++) {
            int instances = 0;
            for (int k = 0; k < p1.getHand().size(); k++) {
                if (p1.getHand().get(k).getPoint() == i) {
                    instances++;
                }
            }
            if (instances == 4) {
                // adds points for every set of 4 similar cards found
                p1.addPoints(1);
            }
        }
        // Checks books found within player 2's hand
        for (int i = 0; i < 13; i++) {
            int instances = 0;
            for (int k = 0; k < p2.getHand().size(); k++) {
                if (p2.getHand().get(k).getPoint() == i) {
                    instances++;
                }
            }
            // increments player 2's points by 2 every time they get a new book
            if (instances == 4) {
                p2.addPoints(1);
            }
        }
    }

    // Prints all the instructions found in the beginning of the game that teach the user how to play
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

    // Check whether the game has ended or not
    public Player checkGameEnd() {
        // Counts both players books
        checkBooks();
        if (p1.getPoints() > 6) {
            // returns player 1 if they won
            return p1;
        } else if (p2.getPoints() > 6) {
            // returns player 2 if they won
            return p2;
        }
        // returns scenario when neither has won
        return null;
    }

    // Checks whether the user input was allowed
    public boolean checkValidTurn(String cardWanted, String turn) {
        // Checks if it was player 1's turn (as the hand checked depends on which player is giving the input)
        if (turn.equals("p1")) {
            for (int i = 0; i < p1.getHand().size(); i++) {
                if (p1.getHand().get(i).getRank().equals(cardWanted)) {
                    // case where they did find the correct card and the turn is valid
                    return true;
                }
            }
        } else {
            for (int i = 0; i < p2.getHand().size(); i++) {
                if (p2.getHand().get(i).getRank().equals(cardWanted)) {
                    // case where they did find the correct card and the turn is valid
                    return true;
                }
            }
        }
        // returns false in which the turn did not work and the player should reenter their answer
        return false;
    }

    // This method checks for the amount of instances a certain card has in the a players hand
    public ArrayList<Card> checkInstances(String nameOfCard, ArrayList<Card> c1) {
        ArrayList<Card> removed = new ArrayList<Card>();
        for (int i = c1.size() - 1; i > -1 ; i--) {
            if (c1.get(i).getRank().equals(nameOfCard)) {
                removed.add(c1.get(i));
            }
        }
        // returns an arraylist of all the indexs in which the card popped up in the given hand
        return removed;
    }

    // Conducts a majority of the logic for player ones turn
    public String player1GameTurn(String response, String turn) {
        // Arraylist of cards that need to be removed from player 2's hand after they are stolen by player 1
        ArrayList<Card> toRemove = checkInstances(response, p2.getHand());
        if (toRemove.isEmpty()) {
            // Scenario where no cards are found matching the specific type and the player must go fish
            // deals a card to the player 1 as they didnt catch anything on their turn
            p1.addCard(deck.deal());
            System.out.println("Whoops, you have to Go Fish!");
            System.out.println("You drew a " + p1.getHand().get(p1.getHand().size() - 1) + ".");
            // returns p2 to indicate that it is now player 2's turn
            return "p2";
        } else {
            // case where instances of a given card were found in the opponents hand
            for (int i = 0; i < toRemove.size(); i++) {
                // adds the stolen cards to player 1's deck
                p1.addCard(toRemove.get(i));
            }
            for (int i = toRemove.size() - 1; i > -1 ; i--) {
                // removes the stolen cards from player 2's deck
                p2.getHand().remove(toRemove.get(i));
            }
            // deals player 2 an additional card as per the rules of go fish
            p2.getHand().add(deck.deal());
            System.out.println("Nice job! You gained " + toRemove.size() + " " + response + "'s.");
            System.out.println("You also forced the opponent to draw a new card. Here comes another turn for you now!");
        }
        // returns p1 to indicate that player 1 gets a repeat turn
        return "p1";
    }

    // Conducts a majority of the logic for player twos turn
    public String player2GameTurn(String response, String turn) {
        // Arraylist of cards that need to be removed from player 1's hand after they are stolen by player 2
        ArrayList<Card> toRemove = checkInstances(response, p1.getHand());
        if (toRemove.isEmpty()) {
            // Scenario where no cards are found matching the specific type and the player must go fish
            // deals a card to the player 2 as they didnt catch anything on their turn
            p2.addCard(deck.deal());
            System.out.println("Whoops, you have to Go Fish!");
            System.out.println("You drew a " + p2.getHand().get(p2.getHand().size() - 1) + ".");
            // returns p1 to indicate that it is now player 1's turn
            return "p1";
        } else {
            // case where instances of a given card were found in the opponents hand
            for (int i = 0; i < toRemove.size(); i++) {
                // adds the stolen cards to player 1's deck
                p2.addCard(toRemove.get(i));
            }
            for (int i = toRemove.size() - 1; i > -1 ; i--) {
                // removes the stolen cards from player 2's deck
                p1.getHand().remove(toRemove.get(i));
            }
            // deals player 2 an additional card as per the rules of go fish
            p1.getHand().add(deck.deal());
            System.out.println("Nice job! You gained " + toRemove.size() + " " + response + "'s.");
            System.out.println("You also forced the opponent to draw a new card. Here comes another turn for you now!");
        }
        // returns p1 to indicate that player 1 gets a repeat turn
        return "p2";
    }

    // Main run game function (calls all other functions in a loop to run the actual game and make it playable)
    public void runGame() {
        // Variables and scanners necessary to play the game and have it work
        Scanner sc = new Scanner(System.in);
        // Prints the instructions
        printInstructions();
        String response;
        String turn = "p1";
        int firstTurn = 0;
        Player possibleWinner = null;
        // Checks if the player is ready to start the game
        do {
            System.out.println("If you are ready to begin type: y");
        } while(!sc.nextLine().equals("y"));
        // Main game loop that runs until a winner is found
        while (possibleWinner ==  null) {
            // Does a turn for player 1
            if (turn.equals("p1")) {
                // Checks if it is the first term, as in the case of the first turn the code needs to format the text differently
                if (firstTurn != 0) {
                    do {
                        System.out.println("If you are ready to continue type: y");
                    } while(!sc.nextLine().equals("y"));
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                }
                firstTurn = 1;
                System.out.println(p1.toString());
                // Gets user input given that the response is invalid (loops this question until it gives a valid question)
                do {
                    System.out.println("Player 1: What card would you like to fish for? (Your answer can be 2-10 or Jack, Queen, King, Ace and must also be a card currently in your hand)");
                    response = sc.nextLine();
                } while (!checkValidTurn(response, turn));
                // conducts player 1's turns and also updates whose turn it is
                turn = player1GameTurn(response, turn);
            }
            // Does a turn for player 2
            if (turn.equals("p2")) {
                // Checks if it is the first term, as in the case of the first turn the code needs to format the text differently
                do {
                    System.out.println("If you are ready to continue type: y");
                } while(!sc.nextLine().equals("y"));
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println(p2.toString());
                // Gets user input given that the response is invalid (loops this question until it gives a valid question)
                do {
                    System.out.println("Player 2: What card would you like to fish for? (Your answer can be 2-10 or Jack, Queen, King, Ace and must also be a card currently in your hand)");
                    response = sc.nextLine();
                } while (!checkValidTurn(response, turn));
                // conducts player 2's turns and also updates whose turn it is
                turn = player2GameTurn(response, turn);
            }
            // Checks if there is a winner to update the while loop condition and break it in case a winner is found
            possibleWinner = checkGameEnd();
        }
        // Prints out congratulations to the winner
        System.out.println("Congrats on winning " + checkGameEnd().getName() + "!");
        System.out.println(checkGameEnd().toString());
    }

    // Main function that can create game objects and is what calls the runGame method
    public static void main(String[] args) {
        CardGame c1 = new CardGame();
        c1.runGame();
    }
}