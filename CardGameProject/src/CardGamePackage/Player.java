package CardGamePackage;

import java.util.ArrayList;

public class Player { // this is not the user, this class includes npcs

// FIELDS
	
	protected ArrayList<Hand> playerHands = new ArrayList<Hand>(); // players can have multiple hands thanks to splitting
	protected String name;

	
// CONSTRUCTOR
	
	public Player(String name) {
		
		playerHands.add(new Hand());
		this.name = name;
		
	}
	
// METHODS
	
	// TODO add more player actions (must apply to both CPU and User)
	
	public void splitHand(Hand handToSplit) {
		// if the hands has only two cards AND the hand has been split less than twice AND the cards are equal
		if( (handToSplit.getSize() == 2) && 
			(handToSplit.getTimesSplit() < Hand.maxSplits) &&
			( 	(handToSplit.getCard(0).getValue() 	== handToSplit.getCard(1).getValue()) || 
				(handToSplit.getCard(0).getRank() 	== handToSplit.getCard(1).getRank())  )){
			
			Hand newHand = new Hand(handToSplit.getCard(1)); // add 2nd card to its own new hand
			handToSplit.removeCard(1); // remove card from source hand
			
			handToSplit.setTimesSplit(handToSplit.getTimesSplit() + 1); // incrementing the times a hand has been split
			newHand.setTimesSplit(newHand.getTimesSplit() + 1);
			
			handToSplit.addCard(Main.currentGame.getDeck().dealCard()); // adds a new card to each hand from the deck
			newHand.addCard(Main.currentGame.getDeck().dealCard()); 
			
			playerHands.add(newHand); // add the new hand to the players hands
			
		} else {
			
			System.out.println("DEBUG: You can't split this hand!"); // this shouldn't happen in game, option shouldn't be offered if they can't split
			
			if( !(handToSplit.getTimesSplit() < Hand.maxSplits) ) {
				System.out.println("DEBUG: handToSplit.getTimesSplit() < Hand.maxSplits");
			}
			
			if(handToSplit.getCard(0).getValue() != handToSplit.getCard(1).getValue()) {
				System.out.println("DEBUG: card 1 doesnt have same value as card 2");
			}
			
		}
		
	}

	public void stand(Hand handToStand) {
		handToStand.setHasBeenStoodOn(true);
	}
	
	public void surrender(Hand handToSurrender) {
		handToSurrender.setActive(false);
		System.out.println("DEBUG: give player back 1/2 wager");
	}
	
// GETTERS AND SETTERS
	
	public String getName() {
		return name;
	}

	public void setName(String playerName) {
		this.name = playerName;
	}
	
	public Hand getHand(int i) {
		return playerHands.get(i);
	}
	
	public Card getCardFromHand(int handIndex, int cardIndex) {
		return playerHands.get(handIndex).getCard(cardIndex);
	}
	
}
