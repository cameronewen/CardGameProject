package CardGamePackage;

public class Dealer { // the "dealer" might not need to do much at all besides having a hand? Not sure yet.
	
// FIELDS
	
	private Hand dealerHand;
	private String name; // i think it would be nice if we gave our dealer a name...

// CONSTRUCTOR
	
	public Dealer() {
		
		dealerHand = new Hand();
		name = "Dealer"; // TODO add dealer name randomization ?
		
	}
	
//METHODS
	
	public void playHand() {
		
		// reveal holecard
		
		// if >= 17, they stand (if soft 17 is on, and theres an ace, they convert it to 1)
		
		// if <= 16, they hit
		
		// they cannot double, split, or surrender. no strats
		
	}
	

// GETTERS AND SETTERS
	
	public Hand getHand() {
		return dealerHand;
	}

	public void setDealerHand(Hand dealerHand) {
		this.dealerHand = dealerHand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Card getUpcard() { // gets dealers upcard (second card in hand)
		
		return dealerHand.getCard(1); 
		
	}
}
