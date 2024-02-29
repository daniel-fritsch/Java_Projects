# War Game

### This is a simulator for the card game WAR which palys a set amount of rounds and sees which player won the game.


##Game play 
WAR is a popular card game played in many variations. The rules of this implementation are below: 

1. Initially shuffle a 52-card standard deck of cards.
2. Deal the cards out completely to two players, alternating cards to each player.
  Each player initially has a collection of 26 cards as their hand. Each player has a hand pile and a discard pile.
3. Do the following until one player is out of cards or until a set number of rounds has been completed:
  Each player plays the top card in their hand.
  If one player's card beats the other (has a higher rank – suits don't matter), that player puts both cards into their discard pile. 
  If the players tie, it is a WAR round, so do the following until the WAR is resolved:
  Each player plays the next card without comparing (in the physical game this would be placing a card “face down” on the table).
  Each player plays one more card and compares in the same way as above.
  The winner of the WAR round takes all played cards (initially compared cards, not compared cards, second compared cards). For a single WAR this will be 6 total cards. All the won cards are added to the discard pile of the winner.
  If the WAR cards also yield a tie, repeat the process (one uncompared card, one compared card) until there is a winner. Thus a single round of the game could in fact have an arbitrary number of card comparisons and put an arbitrary number of cards at risk.
  When a player has no more cards in their hand pile, they take their discard pile, reshuffle it, and continue using it as their hand pile, as before.
  If at any point in the game (even in the middle of a round) a player has no cards remaining in their hand and in their discard pile, they have lost the game.
You can also set a specified number of rounds. In this case, if both players have cards remaining after a set number of rounds (can vary), the player with the most cards is the winner. In this case, it is possible for a tie to occur, with each player having 26 cards in total.
