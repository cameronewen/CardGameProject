package CardGamePackage;

public class CpuPlayer extends Player{

	public CpuPlayer() {
		
		super(RNG.randomName() + " (CPU)", RNG.cpuMoneyRandom());
		
	}

	public void playHandCPU() {
		
		Main.printlnPause(name + "'s turn:\n");
		// Implement basic strategy
		for(int i = 0; i < playerHands.size(); i++) { // cycles through hands
			
			Hand currHand = playerHands.get(i);
			boolean noSplit = false;
			boolean madeMove = false;
			boolean stood = false;
			boolean surrendered = false;
			
			if(playerHands.size() > 1) {
				Main.printlnPause("     " + "Hand " + (i + 1) + ":");
			}
			
			for(int x = 0; x < currHand.getSize(); x++) { 
				
				Main.printlnPause("     " + currHand.getCard(x).getName());
				
			}
			
			System.out.println();
			
			if((currHand.getValue() != 21) && (currHand.isActive()) && !currHand.isBusted()) {
			
				do {
					
					while( (!noSplit) && currHand.canBeSplit() ) {
						
						if(checkIfWillSplit(currHand)) { // check if hand SHOULD be split
							
							splitHand(currHand);
							
							Main.printlnPause("     " + "Splits!\n");
							Main.printlnPause("     New Hand:");
							
							for(int x = 0; x < currHand.getSize(); x++) { // debug printout
								
								Main.printlnPause("     " + currHand.getCard(x).getName());
								
							}
							
							System.out.println();
	
							
						} else {
							
							noSplit = true;
							
							//Main.printlnPause("DEBUG: CPU decided not to split this hand.\n");
							
						}
					
					}
						
				
					
					if(checkIfWillSurrender(currHand)) {
						
						surrender(currHand);
						madeMove = true;
						Main.printlnPause("     " + "Surrender!\n");
						surrendered = true;
						
					}
					
					
					if(checkIfWillDoubleDown(currHand) && !surrendered) { 
						
						if(currHand.getWager() <= money) {// if they can afford it
							
							
							Card card = Main.currentGame.getDeck().dealCard();
							currHand.addCard(card);
							
							Main.printlnPause("     " + "Double Down.\n");
							Main.printlnPause("     " + card.getName() + " (" + currHand.getValue() + ")\n");
							
							currHand.doubleDown();
							
							if(currHand.getValue() > 21) {
								
								Main.printlnPause("     " + "Busts!\n");
								
							}
							
							madeMove = true;
							
						} else {
							
							Main.printlnPause("     " + "Wanted to double down, but can't afford it.\n");
							
						}
						
					}
					
					if(checkIfWillHit(currHand) && !currHand.isDoubledDown() && !surrendered) {
						
						Main.printlnPause("     " + "Hit!\n");
						Card newCard = Main.currentGame.getDeck().dealCard();
						currHand.addCard(newCard);
						Main.printlnPause("     " + newCard.getName() + " (" + currHand.getValue() + ")\n");
						madeMove = true;
						
						if(currHand.getValue() > 21) {
							
							Main.printlnPause("     " + "Busts!\n");
							
						}
						
					}
					
					if(!madeMove) { // if no other move has been made, stand
						
						Main.printlnPause("     " + "Stands.\n");
						stood = true;
						
					}
					
					madeMove = false;
						
				} while (!stood && currHand.isActive() && !surrendered); // while player hasn't stood on hand
			
				System.out.println();
				stood = false;
				surrendered = false;
				
			}
		
		}
		
	}

	public boolean checkIfWillSplit(Hand cpuHand) {
		
		boolean shouldSplit;
		int upcardVal = Main.currentGame.getDealer().getUpcard().getValue();
		
		if(!cpuHand.canBeSplit()) { // if the hand can't be split, return false
			
			Main.printlnPause("CpuPlayer.checkIfWillSplit(): hand can't be split");
			return false;
			
		}
		
		if(cpuHand.getWager() <= money) {
		
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
					Main.printlnPause("DEBUG NOTE: default triggered in CPUPlayer.checkIfWillSplit(). This shouldn't have happened.");
					Main.printlnPause("The card being compared was a " + cpuHand.getCard(0).getRank());
	
				
			}
		
		} else {
			
			//Main.printlnPause(name + " didn't have the money to split. (wager was " + cpuHand.getWager() + " and CPU had " + money + ")");
			shouldSplit = false;
			
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

	public boolean checkIfWillDoubleDown(Hand cpuHand) {
		
		boolean shouldDD = false;
		
		int upcardVal = Main.currentGame.getDealer().getUpcard().getValue();
		
		if(cpuHand.numOfAces() > 0 && cpuHand.getSize() == 2) {
			
			switch(cpuHand.getValue()) {
			
			case 18:
				
				if(upcardVal == 2) {
					
					shouldDD = true;;
					
				}
			
			// THERE ARE NO BREAKS ON PURPOSE, DONT ADD THEM
				
			case 17:
				
				if(upcardVal == 3) {
					
					shouldDD = true;
					
				}
			
			case 16:
			case 15:
				
				if(upcardVal == 4) {
					
					shouldDD = true;
					
				}
			
			case 14:
			case 13:
				
				if(upcardVal == 5) {
					
					shouldDD = true;
					
				}
				
			case 19:
				
				if(upcardVal == 6) {
					
					shouldDD = true;
					
				}
			
				break;
				
			default:
				
				shouldDD = false;
				
			}
			
		} else {
			
			switch(cpuHand.getValue()) {
			
			case 11:
				
				if(upcardVal >= 10) {
					
					shouldDD = true;
					
				}
			
			case 10:
				
				if(upcardVal >= 7 || upcardVal == 2) {
					
					shouldDD = true;
					
				}
			
			case 9:
				
				if(upcardVal > 2 && upcardVal < 7) {
					
					shouldDD = true;
					
				}
				
				break;
			
			default:
				
				shouldDD = false;
				
			}
			
		}
		
		return shouldDD;
	}

	public boolean checkIfWillHit(Hand cpuHand) {
		
		boolean shouldHit = false;
		
		int upcardVal = Main.currentGame.getDealer().getUpcard().getValue();
		
		if(cpuHand.numOfAces() > 0 && cpuHand.getSize() == 2) { // soft totals
			
			switch(cpuHand.getValue()) {
			
			case 18:
				
				if(upcardVal > 8) {
					
					shouldHit = true;
					
				}
				
				break;
				
			case 17:
			case 16:
			case 15:
			case 14:
			case 13:
				
				shouldHit = true;
				break;

			default:
				
				shouldHit = false;

			}
			
		} else { // hard totals
			
			switch(cpuHand.getValue()) {
			
			case 16:
			case 15:
			case 14:
			case 13:
				
				if(upcardVal > 6) {
					
					shouldHit = true;
					
				}
				
				break;
			
			case 12:
				
				if(upcardVal > 6 || upcardVal < 4) {
					
					shouldHit = true;
					
				}
				break;
			
			case 11:
			case 10:
			case 9:
			case 8:
			case 7:
			case 6:
			case 5:
			case 4:
			case 3:
				
				shouldHit = true;
				break;
			
			default:
				
				shouldHit = false;
				
			}
			
		}
		
		return shouldHit;
	}
}
