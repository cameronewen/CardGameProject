package CardGamePackage;

public class Dealer { // the "dealer" might not need to do much at all besides having a hand? Not sure yet.
	
	private Hand dealerHand;
	private String name; // i think it would be nice if we gave our dealer a name...

	public Hand getDealerHand() {
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

	
}