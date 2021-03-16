package CardGamePackage;

public class Dealer { // the "dealer" might not need to do much at all besides having a hand? Not sure yet.
	
// FIELDS
	
	private Hand dealerHand;
	private String name; // i think it would be nice if we gave our dealer a name...

// CONSTRUCTOR
	
	public Dealer() {
		
		dealerHand = new Hand();
		name = RNG.randomName() + " (Dealer)";
		
	}
	
//METHODS
	
	public void playHand() {
		
		Main.printlnPause("\n\n----DEALER'S TURN----\n");
		
		Main.printlnPause("     " + "Dealer's upcard: \n" + "     " + dealerHand.getCard(1).getName() + "\n");
		
		Main.printlnPause("     " + "The dealer reveals hole card: \n");
		Main.printlnPause("     " + dealerHand.getCard(0).getName() + " (" + dealerHand.getValue() + ")\n");
		
		while(dealerHand.getValue() < 17 		|| 
			 (  (dealerHand.getSize() == 2) 	&& 
				(dealerHand.getValue() == 17) 	&&
				(dealerHand.numOfAces() > 0)	&&
				(Main.softSeventeen) 			) ) {
			
			
			Card newCard = Main.currentGame.getDeck().dealCard();
			dealerHand.addCard(newCard);
			Main.printlnPause("     " + "Dealer hits.\n");
			Main.printlnPause("     " + newCard.getName() + " (" + dealerHand.getValue() + ")\n");
			
		}
		
		if(dealerHand.getValue() >= 17 && dealerHand.getValue() < 22) {
			
			Main.printlnPause("     " + "The dealer stands.");
			
		} else {
			
			Main.printlnPause("     " + "The dealer busts.");
			
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
