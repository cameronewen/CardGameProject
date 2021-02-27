package CardGamePackage;
import java.util.Scanner;

public class Main {
	
// FIELDS
	
	public static int numOfDecks = 4; // the default number of decks, changable in settings
	public static int numOfPlayers = 1; // default is 1 on 1 with the dealer, changeable in options
	public static boolean softSeventeen = false; // changes what the dealer does when they have a 17 w/ an ace and a six
	

	
// MAIN
	
	public static void main(String[] args) {
	
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Welcome to BlackJack\n");
        
		menu(scnr);
		
		System.out.println("\nGame Closing");
		
		scnr.close();
		
	}

	
	
// MENUS
	
	public static void menu(Scanner scnr) {
		
		int menuSelection; 
		
		System.out.println("Menu:");
		System.out.println("1. Play\n"
				 + "2. Settings\n"
				 + "3. Rules\n" 
				 + "4. Exit\n");
		
		menuSelection = getIntFromScannerRanged(scnr, 1, 4);
		        
		switch(menuSelection) {

			case 1:
				playGame(scnr);
				break;

			case 2:
				settingsMenu(scnr);
				break;

			case 3:
				gameRules(scnr);
				break;
			
			case 4:
				break;
				
			default:
				System.out.println("Something went wrong with the input scanner.");

		}
		
	}
	
	public static void settingsMenu(Scanner scnr) {
		
		boolean returnToMenu = false;
		
		while(!returnToMenu) {
			System.out.println("1. Number of Decks");
			System.out.println("2. Number of CPU Players");
			System.out.println("3. Toggle Soft 17 (Currently " + softSeventeen + ")");
			System.out.println("4. Return to Menu\n");
			
			int userChoice = getIntFromScannerRanged(scnr, 1, 4);
			
			switch(userChoice) {
				
				case 1:
					System.out.println("Current number of decks: " + numOfDecks);
					System.out.println("Enter desired number of decks (Max 8): ");
					
					numOfDecks = getIntFromScannerRanged(scnr, 1, 8);
					break;
					
				case 2:
					System.out.println("Current number of CPU players: " + (numOfPlayers - 1)); // minus the player
					System.out.println("Enter desired number of CPU players (Max 6): ");
					
					numOfPlayers = getIntFromScannerRanged(scnr, 0, 6) + 1; // the +1 is for the user player
					break;
					
				case 3: 
					softSeventeen = !softSeventeen;
					System.out.println("Soft 17 is now " + softSeventeen);
					break;
				
				case 4: // should spill over to default?
				default:
					returnToMenu = true;
					
			} // end switch
			
		} // end while
			
		menu(scnr);
		
	}
		
	public static void gameRules(Scanner scnr) {
		System.out.println("gameRules() called");
		
		// read gamerules from a .txt using inputstream
		
		System.out.println("Hit enter to return to menu...");
		scnr.next();
		
		menu(scnr);
		
	}

	
	
// GAME
	
	public static void playGame(Scanner scnr) {
		
		System.out.println("playGame() called\n");
		
		System.out.println("Returning to menu\n");
		menu(scnr);
		
	}
	
	
	
// INPUT METHODS
	
	public static int getIntFromScannerRanged(Scanner scnr, int low, int high) { 
			
		/* getIntFromScannerRanged by Cam
		 * 
		 * Returns an integer from user input without having to worry about input mismatch
		 * Specifies a range of accepted input, but I have a version without if we need
		 * unregulated user entered integers.
		 * 
		 */
		
		String userInputString;
		int userNum = low - 1; // initializes the value to keep the while loop running. 
		
		
		
		
		do {
			
			System.out.print(">");
			userInputString = scnr.nextLine();
			System.out.println();
			
			try {
				
				userNum = Integer.parseInt(userInputString.replaceAll(" ", ""));
						
			} catch (NumberFormatException e) {
				
			}
			
			if(userNum < low || userNum > high) {
				System.out.println("Invalid input, please enter a int between " + low + " and " + high + ".");
			}
	    
		} while (userNum < low || userNum > high);
        
        	return userNum;
        
	}
	
	
	
}
