package CardGamePackage;

public class CpuPlayer extends Player{

	public CpuPlayer(String name) {
		
		super(name);
		
	}

	public void playHandCPU() {
		
		// Implement basic strategy
		for(int i = 0; i < playerHands.size(); i++) { // cycles through hands
			
			Hand currHand = playerHands.get(i);
			boolean noSplit = false;
			
			if((currHand.getValue() != 21) && (currHand.isActive()) && !currHand.isBusted()) {
			
				do {
					
					while( (!noSplit) && currHand.canBeSplit() ) {
						
						if(checkIfWillSplit(currHand)) { // check if hand SHOULD be split
							
							splitHand(currHand);
							
							System.out.println("Test Statement: CPU decided to split this hand.");
	
							
						} else {
							
							noSplit = true;
							
							System.out.println("Test Statement: CPU decided not to split this hand.");
							
						}
					
					}
						
					
				// check if they should stand
				// check if they should double down
				// check if they should surrender
				// check if they should hit
					
				} while (!currHand.hasBeenStoodOn()); // while player hasn't stood on hand
			
			}
		
		}
		
	}

	public boolean checkIfWillSplit(Hand cpuHand) {
		
		boolean shouldSplit;
		int upcardVal = Main.dealer.getUpcard().getValue();
		
		if(!cpuHand.canBeSplit()) { // if the hand can't be split, return false
			
			return false;
			
		}
		
		switch(cpuHand.getCard(0).getRank()) {
		
			case ACE: // always split aces and 8s
			case EIGHT:
				
				shouldSplit = true;
				break;
			
			case TWO: // two + three are the same
			case THREE: 
				
				if(upcardVal <= 3) { // if 3 or less
					
					shouldSplit = RNG.randomBoolean();
					
				} else if(upcardVal >= 8) { // if 8 or greater
					
					shouldSplit = false;
					
				} else { // if 4-7
					
					shouldSplit = true;
					
				}
				
				break;
				
			case FOUR: // 4 is unique
				
				if(upcardVal == 5 || upcardVal == 6) {
					
					shouldSplit = RNG.randomBoolean();
					
				} else {
					
					shouldSplit = false;
					
				}
				
				break;
			
			case FIVE: // don't split 5 or 10/face cards in any case
			case TEN:
			case JACK:
			case QUEEN:
			case KING:
				
				shouldSplit = false;
				break;
			
			case SIX:
				
				if(upcardVal == 2) {
					
					shouldSplit = RNG.randomBoolean();
					
				} else if(upcardVal <= 7) {
					
					shouldSplit = true;
					
				} else {
					
					shouldSplit = false;
					
				}
				
				break;
				
			case SEVEN:
				
				if(upcardVal <= 7) {
					
					shouldSplit = true;
					
				} else {
					
					shouldSplit = false;
					
				}
				
				break;
				
			case NINE:
				
				if(upcardVal <= 9 && upcardVal != 7) { // if upcard is < 10 and not seven, split
					
					shouldSplit = true;
					
				} else {
					
					shouldSplit = false;
					
				}
				
				break;
			
			default:
				
				shouldSplit = false;
				System.out.println("DEBUG NOTE: default triggered in CPUPlayer.checkIfWillSplit(). This shouldn't have happened.");
				System.out.println("The card being compared was a " + cpuHand.getCard(0).getRank());

			
		}
		
		return shouldSplit;
		
	}

}
