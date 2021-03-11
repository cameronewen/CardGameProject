package CardGamePackage;
import java.io.*; 
import java.util.Scanner;



public class Main {
	

// FIELDS
	
	public static Game currentGame;
	public static int playerMoney = 1000;
	
	// SETTINGS
	
	public static int numOfDecks = 4; // the default number of decks, changeable in settings
	public static int numOfCpuPlayers = 0; // default is 1 on 1 with the dealer, changeable in options
	
	public static int maxSplits = 2;
	public static int maxWager = 20; // TODO add adjust in settings. maximum wager player can make
	public static int minWager = 2; // TODO add adjust in settings. minimum wager player can make.
	public static boolean softSeventeen = false; // changes what the dealer does when they have a 17 w/ an ace and a six
	public static String playerName = "You";
	
	
// MAIN
	
	public static void main(String[] args) throws IOException {
	
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Welcome to BlackJack\n");
		
		loadSettings(); //TODO create default settings file
		
		mykelTest();
		//camTest();
		
		menu(scnr);
		
		System.out.println("\nGame Closing");
		
		scnr.close();
		
	}

// SAVE / LOAD
	
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
	
	public static void saveSettings() {
		
		// saves player name, money, and settings fields
		// for mykel
		
	}
	
	public static void loadSettings() {
		
		// for mykel
		
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
		
		// - CAM
		// TODO add surrendering on / off? (if added, tell cam to modify cpu logic)
		// TODO add insurance/even money on / off ??? (if added, tell cam to modify cpu logic)
		
		boolean returnToMenu = false;
		
		while(!returnToMenu) {
			System.out.println("1. Player Name:			" + playerName
							+ "\n2. Number of Decks:		" + numOfDecks
							+ "\n3. Number of CPU Players:	" + numOfCpuPlayers
							+ "\n4. Max Splits Per Hand:		" + maxSplits
							+ "\n5. Wager Limits: 		Min " + minWager + ", Max " + maxWager
							+ "\n6. Soft 17:			" + softSeventeen
							+ "\n7. Return to Menu\n");
			
			int userChoice = getIntFromScannerRanged(scnr, 1, 7);
			
			switch(userChoice) {
				
				case 1: 
					System.out.println("Enter new player name: ");
					playerName = scnr.nextLine();
					System.out.println();
					break;
				
				case 2:
					System.out.println("Enter desired number of decks (Max 8): ");
					numOfDecks = getIntFromScannerRanged(scnr, 1, 8);
					break;
					
				case 3:
					System.out.println("Enter desired number of CPU players (Max 6): ");
					numOfCpuPlayers = getIntFromScannerRanged(scnr, 0, 6);
					break;
				
				case 4:
					System.out.println("Enter desired maximum of splits per hand (Max 10): ");
					maxSplits = getIntFromScannerRanged(scnr, 0, 10);
					break;
				
				case 5:
					System.out.println("Enter minimum wager: (Max 100)");
					minWager = getIntFromScannerRanged(scnr, 0, 100);
					System.out.println("Enter maximum wager: (Between " + minWager + " and 100");
					maxWager = getIntFromScannerRanged(scnr, minWager, 100);
					break;
					
				case 6: 
					softSeventeen = !softSeventeen;
					System.out.println("Soft 17 is now " + softSeventeen);
					break;
				
				
				default:
					returnToMenu = true;
					break;
					
				} // end switch
			
			saveSettings();
			
		} // end while
			
		menu(scnr);
		
	}
		


	
// GAME
	
	public static void playGame(Scanner scnr) throws IOException {
		
		System.out.println("playGame() called\n");
		
		currentGame = new Game();
		
		currentGame.placeWagers(); // cam
		
		currentGame.dealCards(); // mykel
		
		currentGame.playHands(); // cam

		currentGame.payout(); // cam
		
		currentGame = null;
		
		saveSettings(); // mykel ( do after cam finishes settings )
		
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
		
		Game testDeck = new Game();
		
		testDeck.dealCards();
		
	

		
	}
	
	public static void camTest() {
		
		
//		currentGame = new Game(); // add new game
//		currentGame.setDeck(new Deck()); // add deck to draw from
//		currentGame.clearCpuList(); // clear cpus
//		currentGame.addCpu(new CpuPlayer()); // add new plain cpu
//		
//		Hand cpuHand = currentGame.getCpu(0).getHand(0);
//		Hand dealerHand = currentGame.getDealer().getHand();
//		CpuPlayer cpu = currentGame.getCpu(0);
//		
//		dealerHand.addCard(new Card(Rank.TWO, Suit.HEARTS));
//		dealerHand.addCard(new Card(Rank.TEN, Suit.CLUBS));
//		
//		cpuHand.addCard(new Card(Rank.EIGHT, Suit.HEARTS));
//		cpuHand.addCard(new Card(Rank.EIGHT, Suit.HEARTS));
//		
//		cpu.playHandCPU();
		
		for(int i = 0; i < 100; i++) {
			CpuPlayer newCpu = new CpuPlayer();
			System.out.println(newCpu.getName());
		}
		
		
	}
	
	public static void dawsonTest() {
		
		
		
	}
	
	
}
