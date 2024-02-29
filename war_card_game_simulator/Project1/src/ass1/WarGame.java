package ass1;

//The game is initialized with 200 round being played. If you would like to change
//this go to the bootom of the code and look at the main method where this can be adjusted. 

public class WarGame {
    private SQueue<Card> deckPlayer1;
    private SQueue<Card> deckPlayer2;
    static long seed = 12312311;

    
    //Also: Im not sure if the sample war_game_outputs have the game constructed like this
    //but I added the cards for war back to the hands of the player that won. This way the total number
    //of cards between players is always 52. This is how I learned to play the game and thats how I understood
    //the rules that were enumerated in the assignment document. 
    public WarGame(int roundsNum) {
    	//initiailizes deck
    	Card[] deck = initializeDeck();
    	
    	
    	//This is responsible for caling shuffle method from SQUEUE in order
    	//to shuffle the deck before the game begins. 
    	SQueue<Card> deckShuffleFirst = new SQueue<>(52);
        for (Card card : deck) {
            deckShuffleFirst.enqueue(card);
        }
        //this is where the seed is determined. Right now this will always give a 
        //random output becuase seed is not passed as a paramter to shuffle. If you
        //want seed output just write deckShuffleFirst.shuffle(seed); and it will always
        //return the same output value. 
        deckShuffleFirst.shuffle();
        for (int i = 0; i < 52; i++) {
            deck[i] = deckShuffleFirst.dequeue();
        }
        
        
        //Each player gets a deck based on how cards are dealt
    	deckPlayer1 = new SQueue<>(52);
    	deckPlayer2 = new SQueue<>(52);
        

        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0) {
            	deckPlayer1.enqueue(deck[i]);
            } else {
            	deckPlayer2.enqueue(deck[i]);
            }
        }
      
        //If you want to shuffle the individual decks each player
        //recieves just de-comment the next two lines and the decks will
        //again be shuffle once the players each get a deck. 
        
        //deckPlayer1.shuffle();
        //deckPlayer2.shuffle();
        
        
        //shows the original hands after shuffle
        System.out.println("Initial Decks of Both Players:");
        System.out.println("Player 1's original deck before game has started: " + deckPlayer1);
        System.out.println("Player 2's original deck before game has started: " + deckPlayer2);
        System.out.println("");
        
        //this is what does chosen num of rounds.
        for (int round = 1; round <= roundsNum; round++) {
            System.out.println("Round " + round + ":");
            if (!round()) {
                break; 
            }
        }

        winnerDecision();
    }

    private Card[] initializeDeck() {
        Card[] deck = new Card[52];
        int index = 0;

        for (Card.Suits suitType : Card.Suits.values()) {
            for (Card.Ranks rank : Card.Ranks.values()) {
                deck[index] = new Card(suitType, rank);
                index++;
            }
        }

        return deck;
    }

    
    //Determines the conditions for playing a round and prints out the information 
    //to the user so they can see who won each round and what cards were played for that.
    
 
    private boolean round() {
        try {
            if (deckPlayer1.isEmpty() || deckPlayer2.isEmpty()) {
                System.out.println("The Game is Over!");
                return false;
            }
            
            //Two cards are in this case taken which are then to be compared. This is the dequeue process stuff
            Card Card1 = deckPlayer1.dequeue();
            Card Card2 = deckPlayer2.dequeue();

            System.out.println("Player 1 plays card: " + Card1);
            System.out.println("Player 2 plays card: " + Card2);
            
            //checks who has won round and prinst result or calls a war
            if (Card1.compareTo(Card2) > 0) {
            	deckPlayer1.enqueue(Card1);
            	deckPlayer1.enqueue(Card2);
                System.out.println("Player 1 has won the round!");
                System.out.println("");
            } else if (Card1.compareTo(Card2) < 0) {
            	deckPlayer2.enqueue(Card2);
            	deckPlayer2.enqueue(Card1);
                System.out.println("Player 2 has won the round!");
                System.out.println("");
            } else {
                System.out.println("WAR!");
                handleWar(Card1, Card2);
            }
        } catch (EmptyQueueException | FullQueueException check) {
            check.printStackTrace();
        }

        return true;
    }
    
    //Handles war cases and makes sure to print out the situations so the
    //viewer cna see who ends up winning the war and what cards were played. 
    private void handleWar(Card player1Card, Card player2Card) {
        SQueue<Card> warPile = new SQueue<>(52);
        warPile.enqueue(player1Card);
        warPile.enqueue(player2Card);

        while (player1Card.compareTo(player2Card) == 0) {
            if (deckPlayer1.isEmpty() || deckPlayer2.isEmpty()) {
                System.out.println("WAR ended without concluding. Not enough cards in deck.");
                return;
            }

            try {
                warPile.enqueue(deckPlayer1.dequeue());
                warPile.enqueue(deckPlayer2.dequeue());

                player1Card = deckPlayer1.dequeue();
                player2Card = deckPlayer2.dequeue();

                warPile.enqueue(player1Card);
                warPile.enqueue(player2Card);

                //cards played by both sides in war
                System.out.println("Player 1 plays card: " + player1Card);
                System.out.println("Player 2 plays card: " + player2Card);
            } catch (EmptyQueueException | FullQueueException check) {
                check.printStackTrace();
            }
        }
        
        //card comparison to determine war - followed be enqueue for winner or continuation in case of draw
        if (player1Card.compareTo(player2Card) > 0) {
            while (!warPile.isEmpty()) {
            	deckPlayer1.enqueue(warPile.dequeue());
            }
            System.out.println("Player 1 has won the WAR!");
            System.out.println("");
        } else {
            while (!warPile.isEmpty()) {
            	deckPlayer2.enqueue(warPile.dequeue());
            }
            System.out.println("Player 2 has won the WAR!");
            System.out.println("");
        }

 
    }

    //This one is used to decide who the eventual winner of the game is and print the 
    //output designed to convery that information to the user. 
    private void winnerDecision() {
        System.out.println("\nThe Game is Over!");

        int deckSize1 = deckPlayer1.getSize();
        int deckSize2 = deckPlayer2.getSize();

        System.out.println("Player 1 has " + deckSize1 + " cards remaining.");
        System.out.println("Player 2 has " + deckSize2 + " cards remaining.");

        if (deckSize1 > deckSize2) {
            System.out.println("Player 1 has won the game!");
        } else if (deckSize1 < deckSize2) {
            System.out.println("Player 2 has won the game!");
        } else {
            System.out.println("It is a tie!");
        }

        System.out.println("\nFinal Decks of Both Players:");
        System.out.println("Player 1's final deck: " + deckPlayer1);
        System.out.println("Player 2's final deck: " + deckPlayer2);
    }

    public static void main(String[] args) {
        int numRounds = 200; // Default number of rounds is currently 200 if no user input is given!
        if (args.length > 0) {
            try {
                numRounds = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("This is invalid input so I am using default rounds of 200!");
            }
        }

        WarGame warGame = new WarGame(numRounds);
    }
}
