package CardGamePackage;

import java.util.ArrayList;

public class Hand {

// FIELDS
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int value;						// total value of cards in hand
	private boolean hasAce = false;			// does the hand have an ace?
	private boolean isBusted = false; 		// hand value goes over 21
	private boolean doubledDown = false; 	// no more cards can be added to hand
	private boolean isActive = true; 		// player surrender (diff from bust)
	private int timesSplit = 0; 			// number of times hand has been split
	private int wager;						// the players wager on this hand
	
// CONSTRUCTOR
	
	public Hand() {
		
		value = 0;
		
	}
	
	public Hand(Card card1) {
		
		hand.add(card1);
		
	}
	
// METHODS
	
	public void addCard(Card newCard) {
		
		if(!doubledDown) {
			
			hand.add(newCard);
			
		} else {
			
			System.out.println("This hand has been doubled down, and no more cards can be added.");
			
		}
		
		countValue();
		
	}
	
	public void removeCard(int index) {
		
		hand.remove(index);
		
	}
	
	public void countValue() {
		
		// TODO fill in with value logic wrt aces.
		
	}
	
	public void doubleDown() {
		
		doubledDown = true;
		wager *= 2;
		
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
	
	public boolean hasAce() {
		
		return hasAce;
		
	}
	
	public void setHasAce(boolean hasAce) {
		
		this.hasAce = hasAce;
		
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
	
	
	
	

	
	
}
