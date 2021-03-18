package CardGamePackage;

import java.util.Scanner;

public class User extends Player{

	public User(double playerMoney) {
		
		super(Main.playerName, playerMoney);
		
	}
	
	public User() {
		
		super(Main.playerName, Main.playerMoney);
		
	}
	
	public void playHand(Scanner scnr) {
		
		// TODO add player moves
		
		Main.printlnPause("Your turn: \n");
		
		if(Main.currentGame.getDeck().getCardCount() > 0) { // adds + if its higher than 0.
			
			Main.printlnPause("Current card count: +" + Main.currentGame.getDeck().getCardCount() + "\n");
			
		} else {
			
			Main.printlnPause("Current card count: " + Main.currentGame.getDeck().getCardCount() + "\n");
			
		}
		
		for(int i = 0; i < playerHands.size(); i ++) {
			
			Hand currHand = playerHands.get(i);
			int userInput = 0;
			
			if(playerHands.size() > 1) {
				
				Main.printlnPause("     " + "Hand " + (i + 1) + ":");
				
			}
			
			for(int x = 0; x < currHand.getSize(); x++) { 
				
				Main.printlnPause("     " + currHand.getCard(x).getName());
				
			}
			
			System.out.println();
			
			if(currHand.isBlackjack()) {
				
				Main.printlnPause("     Blackjack!");
				
			}
			
			while(currHand.isActive() && !currHand.hasBeenStoodOn() && !currHand.isDoubledDown() && !currHand.isBusted() && !currHand.isBlackjack()) {// inner while loop checking if they've stood / doubled down / surrendered their hand
				
				Main.printlnPause("1. Stand");
				Main.printlnPause("2. Hit");
				Main.printlnPause("3. Double Down");
				Main.printlnPause("4. Surrender");
				
				if(currHand.canBeSplit()) {
					
					Main.printlnPause("5. Split Hand");
					System.out.println();
					userInput = Main.getIntFromScannerRanged(scnr, 1, 5);
					
				} else {
					
					System.out.println();
					userInput = Main.getIntFromScannerRanged(scnr, 1, 4);
					
				}
				
				switch(userInput) {
				
				case 1: // Stand
					
					Main.printlnPause("     Stood.\n");
					currHand.setHasBeenStoodOn(true);
					
					break;
					
				case 2: // Hit
					
					Card newCard = Main.currentGame.getDeck().dealCard();
					currHand.addCard(newCard);
					
					Main.printlnPause("     Hit!\n");
					Main.printlnPause("     " + newCard.getName() + "(" + currHand.getValue() + ")" + "\n");
					
					break;
					
				case 3: // Double Down
					
					currHand.doubleDown();
					
					Card card = Main.currentGame.getDeck().dealCard();
					
					Main.printlnPause("     Doubled Down!" + "\n");
					
					Main.printlnPause("     " + card.getName() + "(" + currHand.getValue() + ")" + "\n");
					
					break;
					
				case 4: // Surrender
					
					surrender(currHand);
					Main.printlnPause("     " + "Surrender!\n");
					
					break;
					
				case 5: // Split
					
					splitHand(currHand);
					
					Main.printlnPause("     " + "Splits!\n");
					Main.printlnPause("     New Hand:");
					
					for(int x = 0; x < currHand.getSize(); x++) { // debug printout
						
						Main.printlnPause("     " + currHand.getCard(x).getName());
						
					}
					
					System.out.println();
					
					break;
					
				default:
					
					System.out.println("DEBUG: something went wrong with user.playHand switch");
					
				}
				
				if(currHand.getValue() > 21) {
					
					Main.printlnPause("     Busted!\n");
					
				}
			
			} // end while loop
			
			System.out.println();
		
		}	// end for loop
	
		System.out.println();
		
	}

}
