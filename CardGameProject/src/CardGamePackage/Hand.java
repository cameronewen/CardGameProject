package CardGamePackage;

import java.util.ArrayList;

public class Hand {

// FIELDS
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int value;
	private boolean hasAce = false;
	private boolean isBusted = false;
	
	
// METHODS
	
	public void addCard(Card newCard) {
		
		hand.add(newCard);
		
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
	
	
	
	

	
	
}
