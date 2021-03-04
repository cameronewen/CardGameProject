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
			(handToSplit.getTimesSplit() < 2) &&
			(handToSplit.getCard(0).getValue() == handToSplit.getCard(1).getValue()) ) {
			
			Hand newHand = new Hand(handToSplit.getCard(1)); // add 2nd card to its own new hand
			handToSplit.removeCard(1); // remove card from source hand
			
			handToSplit.setTimesSplit(handToSplit.getTimesSplit() + 1); // incrementing the times a hand has been split
			newHand.setTimesSplit(newHand.getTimesSplit() + 1);
			
			playerHands.add(newHand); // add the new hand to the players hands
			
		} else {
			
			System.out.println("You can't split this hand!"); // this shouldn't happen in game, option shouldn't be offered if they can't split
			
		}
	}

	public void Stand(Hand handToStand) {
		handToStand.setHasBeenStoodOn(true);
	}
	
// GETTERS AND SETTERS
	
	public String getName() {
		return name;
	}

	public void setName(String playerName) {
		this.name = playerName;
	}
	
}
