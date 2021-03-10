package CardGamePackage;
import java.io.*; 
import java.util.Scanner;



public class Main {
	

// FIELDS
	
	public static int numOfDecks = 4; // the default number of decks, changeable in settings
	public static int numOfPlayers = 1; // default is 1 on 1 with the dealer, changeable in options
	public static boolean softSeventeen = false; // changes what the dealer does when they have a 17 w/ an ace and a six
	public static int maxSplits = 2;
	public static Game currentGame;
	public static int playerMoney = 1000;
	public static String playerName = "You";
	
// MAIN
	
	public static void main(String[] args) throws IOException {
	
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Welcome to BlackJack\n");
		mykelTest();
		
		menu(scnr);
		//camTest();
		
		System.out.println("\nGame Closing");
		
		scnr.close();
		
	}

	
	
// MENUS
	
	public static void menu(Scanner scnr) throws IOException {
		
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
	
	public static void settingsMenu(Scanner scnr) throws IOException {
		
		//TODO add casino default mode / more blackjack variant rules
		
		// TODO add player name setter (default is "you"
		// TODO add max wager setting
		// TODO add surrendering on / off? (if added, tell cam to modify cpu logic)
		// TODO add insurance/even money on / off ??? (if added, tell cam to modify cpu logic)
		
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
		
	public static void gameRules(Scanner scnr) throws IOException {
		
		//input by My'kel
		// TODO update gameplay.txt file to reflect game rules
	
		FileInputStream fileInputStream = new FileInputStream("src/gameplay.txt");
		Scanner fileInput = new Scanner(fileInputStream);
		
		while(fileInput.hasNextLine()) {
			System.out.println(fileInput.nextLine());
		}
		
		
		System.out.println();
		
		fileInputStream.close();
		fileInput.close();
		
		System.out.println();
		System.out.println("Hit enter to return to menu...");
		scnr.nextLine();
		
		menu(scnr);
		
	}

	
	
// GAME
	
	public static void playGame(Scanner scnr) throws IOException {
		
		System.out.println("playGame() called\n");
		
		// load current money from file?
		
		currentGame = new Game();
		
		// player places wager on hand, then cpus (TODO add max wager setting)
		
		//TODO deal cards to all players ( have an arraylist of cpuplayers thats cycled through?) (User -> Any CPUs -> Dealer)
				// print cards of players as they're dealed, except for dealer, whose upcard is printed
		
		//TODO users turn (dynamic hit, stand, split, double down, surrender)
		
		//TODO CPUs turns
		
		//TODO dealer turns
		
		//TODO payouts / save new total to file
		
		// release currentGame add method to Game to release all objects to save space.
		
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
				
				System.out.println("Fail.");
				
			}
			
			if(userNum < low || userNum > high) {
				System.out.println("Invalid input, please enter a int between " + low + " and " + high + ".");
			}
	    
		} while (userNum < low || userNum > high);
        
        	return userNum;
        
	}
	
// DEBUG METHODS
	
	public static void mykelTest() {
		
		Deck testDeck = new Deck();
		testDeck.shuffleDeck();
	
		for(int i = 0; i < testDeck.getDeckSize(); i++) {
			
			System.out.println(testDeck.getCardAtIndex(i).getName());
		
		}

		
	}
	
	public static void camTest() {
		
		
		currentGame = new Game(); // add new game
		currentGame.setDeck(new Deck()); // add deck to draw from
		currentGame.clearCpuList(); // clear cpus
		currentGame.addCpu(new CpuPlayer()); // add new plain cpu
		
		Hand cpuHand = currentGame.getCpu(0).getHand(0);
		Hand dealerHand = currentGame.getDealer().getHand();
		CpuPlayer cpu = currentGame.getCpu(0);
		
		dealerHand.addCard(new Card(Rank.TWO, Suit.HEARTS));
		dealerHand.addCard(new Card(Rank.TEN, Suit.CLUBS));
		
		cpuHand.addCard(new Card(Rank.EIGHT, Suit.HEARTS));
		cpuHand.addCard(new Card(Rank.EIGHT, Suit.HEARTS));
		
		cpu.playHandCPU();
		
		
	}
	
	public static void dawsonTest() {
		
		
		
	}
	
	
}
