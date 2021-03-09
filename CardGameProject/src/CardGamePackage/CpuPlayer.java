package CardGamePackage;

public class CpuPlayer extends Player{

	public CpuPlayer() {
		
		super(RNG.randomName());
		
	}

	public void playHandCPU() {
		
		// Implement basic strategy
		for(int i = 0; i < playerHands.size(); i++) { // cycles through hands
			
			Hand currHand = playerHands.get(i);
			boolean noSplit = false;
			
//			System.out.println("Playing hand: " + i); // DEBUG FIXME
//			
			for(int x = 0; x < currHand.getSize(); x++) { // DEBUG PRINTOUT FIXME
				System.out.println("Card " + x + ": " + currHand.getCard(x).getName());
			}
			
			if((currHand.getValue() != 21) && (currHand.isActive()) && !currHand.isBusted()) {
			
				do {
					
					while( (!noSplit) && currHand.canBeSplit() ) {
						
						if(checkIfWillSplit(currHand)) { // check if hand SHOULD be split
							
							splitHand(currHand);
							
							for(int x = 0; x < currHand.getSize(); x++) { // DEBUG PRINTOUT FIXME
								System.out.println("Card " + x + ": " + currHand.getCard(x).getName());
							}
							
							System.out.println("DEBUG: CPU decided to split this hand.");
	
							
						} else {
							
							noSplit = true;
							
							System.out.println("DEBUG: CPU decided not to split this hand.");
							
						}
					
					}
						
				
					
				if(checkIfWillSurrender(currHand)) { //FIXME add surrender on/off in settings
					
					surrender(currHand);
					System.out.println("DEBUG: CPU decided to surrender");
					
				}
				
				// double down
				// check if they should hit
				// check if they should stand
					
				} while (!currHand.hasBeenStoodOn() || !currHand.isActive()); // while player hasn't stood on hand
			
			}
		
		}
		
	}

	public boolean checkIfWillSplit(Hand cpuHand) {
		
		boolean shouldSplit;
		int upcardVal = Main.currentGame.getDealer().getUpcard().getValue();
		
		if(!cpuHand.canBeSplit()) { // if the hand can't be split, return false
			
			System.out.println("CpuPlayer.checkIfWillSplit(): hand can't be split");
			return false;
			
		}
		
		switch(cpuHand.getCard(0).getRank()) {
		
			case ACE:// always split aces and 8s
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

	public boolean checkIfWillSurrender(Hand cpuHand) {
		
		int upcardVal = Main.currentGame.getDealer().getUpcard().getValue();
		int handVal = cpuHand.getValue();
		boolean result;
		
		if( ((upcardVal == 9 || upcardVal == 10 || upcardVal == 11) && handVal == 16) ||
			((upcardVal == 10) && (handVal == 15))) {
			
			result = true;
			
		} else if (Main.softSeventeen && ((upcardVal == 11 || upcardVal == 1) && (handVal == 15 || handVal == 17))) {
			
			result = true;
			
		} else {
			
			result = false;
			
		}
		
		return result;
		
	}
}
