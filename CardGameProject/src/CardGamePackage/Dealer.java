package CardGamePackage;

public class Dealer { // the "dealer" might not need to do much at all besides having a hand? Not sure yet.
	
// FIELDS
	
	private Hand dealerHand;
	private String name; // i think it would be nice if we gave our dealer a name...

// CONSTRUCTOR
	
	public Dealer() {
		
		dealerHand = new Hand();
		name = RNG.randomName() + " (Dealer)"; // TODO add dealer name randomization ?
		
	}
	
//METHODS
	
	public void playHand() {
		
		System.out.println("Dealer's upcard: " + dealerHand.getCard(1).getName() + "\n");
		
		System.out.println("The dealer reveals hole card: \n");
		System.out.println(dealerHand.getCard(0).getName() + " (" + dealerHand.getValue() + ")\n");
		
		while(dealerHand.getValue() < 17 		|| 
			 (  (dealerHand.getSize() == 2) 	&& 
				(dealerHand.getValue() == 17) 	&&
				(dealerHand.numOfAces() > 0)	&&
				(Main.softSeventeen) 			) ) {
			
			
			Card newCard = Main.currentGame.getDeck().dealCard();
			dealerHand.addCard(newCard);
			System.out.println("Dealer hits.\n");
			System.out.println(newCard.getName() + " (" + dealerHand.getValue() + ")\n");
			
			
		}
		
		if(dealerHand.getValue() >= 17 && dealerHand.getValue() < 22) {
			
			System.out.println("The dealer stands.");
			
		} else {
			
			System.out.println("The dealer busts.");
			
		}
		
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
