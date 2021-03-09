package CardGamePackage;

import java.util.ArrayList;

public class Hand {

// FIELDS
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int value;						// total value of cards in hand
	private int numOfAces = 0;				// how many aces are in the hand
	private int numOfAcesSetToOne = 0;		// how many of those aces have had their value changed to 1
	private boolean isBusted = false; 		// hand value goes over 21
	private boolean doubledDown = false; 	// no more cards can be added to hand
	private boolean isActive = true; 		// player surrender (diff from bust)
	private boolean canBeSplit = false;		// checks if the deck can be split
	private boolean hasBeenStoodOn = false;			// player stood on hand or not
	private int maxSplits = 2;				// max number of splits, TODO make changeable from settings
	private int timesSplit = 0; 			// number of times hand has been split
	private int wager;						// the players wager on this hand
	
// CONSTRUCTOR
	
	public Hand() {
		
		value = 0;
		
	}
	
	public Hand(Card card1) {
		
		addCard(card1);
		
	}
	
// METHODS
	
	public void addCard(Card newCard) { // TODO (MAYBE) add debug statement for dealer hitting after card value >= 17
		
		if(!doubledDown && isActive && !isBusted) { // checks if hand can get new card (not dd / active / under 21)
			
			if(newCard.getRank() == Rank.ACE) { // if the new card is an ace, add an ace to the counter
				
				numOfAces++;
				
			}
			
			hand.add(newCard); // adds card to hand
			
			checkCanBeSplit();
			
		} else { // debug statements, the player shouldn't have the option to hit if any of this is true
			
			System.out.println("No more cards can be added to this hand.");
			
			if(doubledDown) {
				System.out.println("DEBUG MESSAGE: Player attempted to add card while doubled down.");
			}
			
			if(isBusted) {
				System.out.println("DEBUG MESSAGE: Player attempted to add card while busted.");
			}
			
			if(!isActive) {
				System.out.println("DEBUG MESSAGE: Player attempted to add card to inactive hand.");
			}
			
		}
		
		countValue();
		
	}
	
	public void removeCard(int index) {
		
		hand.remove(index);
		
	}
	
	public void countValue() {
		
		value = 0;
		
		for(int i = 0; i < hand.size(); i++) {
			
			value += hand.get(i).getValue();
			
		}
		
		if(value > 21) {
			
			checkForBust();
			
		}
		
	}
	
	public void doubleDown() {
		
		if(!doubledDown) {
			
			doubledDown = true;
			wager *= 2;
			
		} else {
			
			System.out.println("DEBUG MESSAGE: Hand has already been doubled down");
			
		}
		
	}

	public void checkForBust() { // checks if card has busted
		
		if(value > 21 && numOfAces == numOfAcesSetToOne) { // if there are no more aces to lower value of
			
			isActive = false;
			isBusted = true;
			
		} else if(value > 21 && numOfAces > numOfAcesSetToOne) {
			
			int index = 0;
			
			while( hand.get(index).getValue() != 11 ) {
				
				// while the card at index isn't an ace valued at 11
				
				index++;

				if(index >= hand.size()) {
					
					System.out.println("DEBUG: The error occured at Hand.checkForBust() while searching for an ace not set to 1.");
				
				}
				
			}
			
			hand.get(index).setValue(1);
			numOfAcesSetToOne++;
			
			countValue(); // calls countValue again
			
		} else if(value > 21 && numOfAces < numOfAcesSetToOne) {
			
			System.out.println("DEBUG MESSAGE: More aces set to 1 than in hand???");
			
		} 
		
	}

	public void checkCanBeSplit() {	// checks if deck is able to be split
			
			if( (hand.size() == 2) && // hand has two cards
				(hand.get(0).getValue() == hand.get(1).getValue()) && // cards are of same value
				(timesSplit < maxSplits) ) { // cards have been split less than the maximum number of times
				
				canBeSplit = true;
				
			} else {
				
				canBeSplit = false;
				
			}
		
	}

	
// GETTERS AND SETTERS
	
	// Value of cards (ace = 11 by default)
	
	public int getValue() {
		
		return value;
		
	}
	
	public void setValue(int value) {
		
		this.value = value;
		
	}
	
	// Does it have an ace?
	
	public int numOfAces() {
		
		return numOfAces;
		
	}
	
	public void setNumOfAces(int numOfAces) {
		
		this.numOfAces = numOfAces;
		
	}
	
	// Busted?
	
	public boolean isBusted() {
		
		return isBusted;
		
	}
	
	public void setBusted(boolean isBusted) {
		
		this.isBusted = isBusted;
		
	}
	
	// card checks
	
	public Card getCard(int index) {
		
		return hand.get(index);
				
	}
	
	public int getSize() {
		
		return hand.size();
		
	}
	
	// number of times a hand has been split

	public int getTimesSplit() {
		
		return timesSplit;
		
	}

	public void setTimesSplit(int timesSplit) {
		
		this.timesSplit = timesSplit;
		
	}
	
	// wager on the current hand

	public int getWager() {
		return wager;
	}

	public void setWager(int wager) {
		this.wager = wager;
	}
	
	// double down

	public boolean isDoubledDown() {
		return doubledDown;
	}

	public void setDoubledDown(boolean doubledDown) {
		this.doubledDown = doubledDown;
		
	}

	// check if hand is active
	public boolean isActive() {
		
		return isActive;
		
	}

	public void setActive(boolean isActive) {
		
		this.isActive = isActive;
		
	}

	public boolean canBeSplit() {
		
		return canBeSplit;
		
	}

	// stand
	
	public boolean hasBeenStoodOn() {
		return hasBeenStoodOn;
	}

	public void setHasBeenStoodOn(boolean hasBeenStoodOn) {
		this.hasBeenStoodOn = hasBeenStoodOn;
	}
	
	
}
