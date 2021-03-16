package CardGamePackage;
import java.io.*; 
import java.util.Scanner;


public class Main {
	

// FIELDS
	
	public static Game currentGame;
	public static int playerMoney = 1000;
	
	// SETTINGS
	
	public static int numOfDecks = 4; 		// the default number of decks, changeable in settings
	public static int numOfCpuPlayers = 4; 	// default is 1 on 1 with the dealer, changeable in options
	public static int maxSplits = 2;
	public static int maxWager = 20; 		// maximum wager player can make.
	public static int minWager = 2; 		// minimum wager player can make.
	public static boolean softSeventeen = false; // changes what the dealer does when they have a 17 w/ an ace and a six
	public static String playerName = "You";
	
	public static int textSpeed = 50; // change this to change printout speed (50 is a good speed for regular play)
	
	
// MAIN
	
	public static void main(String[] args) throws IOException {
	
		Scanner scnr = new Scanner(System.in);
		
		//loadSettings();
		
		printlnPause("Welcome to BlackJack\n");
		
		//TODO create default settings file
		
		
		//mykelTest();
		//camTest(scnr);
		
		menu(scnr);
		
		printlnPause("\nGame Closing");
		
		scnr.close();
		
	}

// SAVE / LOAD
	
	public static void gameRules(Scanner scnr) throws IOException {
		
		//input by My'kel
		
	
		FileInputStream fileInputStream = new FileInputStream("src/gameplay.txt");
		Scanner fileInput = new Scanner(fileInputStream);
		
		while(fileInput.hasNextLine()) {
			printlnPause(fileInput.nextLine());
		}
		
		
		System.out.println();
		
		fileInputStream.close();
		fileInput.close();
		
		System.out.println();
		printlnPause("Hit enter to return to menu...");
		scnr.nextLine();
		
		menu(scnr);
		
	}
	
	public static void defaultSettings() {
		
		 numOfDecks = 4; 
		 numOfCpuPlayers = 0; 
		 maxSplits = 2;
		 maxWager = 20; 
		 minWager = 2; 
		 softSeventeen = false;
		 playerName = "You";
		
	}
	
	public static void saveSettings() throws IOException {
	//  mykel
		
		FileWriter settings = new FileWriter("src/settings.txt");// saves player name, money, and settings fields
		
		settings.write(playerMoney + "\n");// for mykel
		settings.write(numOfDecks + "\n");
		settings.write(numOfCpuPlayers + "\n");
		settings.write(maxWager + "\n" );
		settings.write(minWager + "\n" );
		
		if (softSeventeen) {
			settings.write("true\n");
		} else {
			settings.write("false\n");
		}
		
		settings.write(playerName);
		
		settings.flush();
	    settings.close();
	    
	}
	
	public static void loadSettings() throws IOException {
		
		//  mykel
		FileInputStream settingsInputStream = new FileInputStream("src/settings.txt");
		Scanner settingsInput = new Scanner(settingsInputStream);
		
		 
		
		if(settingsInput.hasNextLine()) {
			playerMoney = Integer.parseInt(settingsInput.nextLine());
		}
		if(settingsInput.hasNextLine()) {
			numOfDecks = Integer.parseInt(settingsInput.nextLine());
		}
		if(settingsInput.hasNextLine()) {
			numOfCpuPlayers = Integer.parseInt(settingsInput.nextLine());
		}
		if(settingsInput.hasNextLine()) {
			maxWager = Integer.parseInt(settingsInput.nextLine());
		}
		if(settingsInput.hasNextLine()) {
			minWager = Integer.parseInt(settingsInput.nextLine());
		}
		
		if(settingsInput.hasNextLine()) {
			if (settingsInput.nextLine().equals("true")) {
				softSeventeen = true;
				
			}else if (settingsInput.nextLine().equals("false")) {
				softSeventeen = false;
				
			}else {
				printlnPause("Something went wrong with load/save methods");
			}
			
			
		}
		
		if(settingsInput.hasNextLine()) {
			playerName = settingsInput.nextLine();
		}
		
		
		
		System.out.println();
		
		settingsInputStream.close();
		settingsInput.close();
		
		System.out.println();
		
	}
	
// MENUS
	
	public static void menu(Scanner scnr) throws IOException {
		
		int menuSelection; 
		
		printlnPause("Menu:");
		printlnPause("1. Play\n"
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
				printlnPause("Something went wrong with the input scanner.");

		}
		
	}
	
	public static void settingsMenu(Scanner scnr) throws IOException {
		
		// - CAM
		
		boolean returnToMenu = false;
		
		while(!returnToMenu) {
			printlnPause("1. Player Name:			" + playerName
							+ "\n2. Number of Decks:		" + numOfDecks
							+ "\n3. Number of CPU Players:	" + numOfCpuPlayers
							+ "\n4. Max Splits Per Hand:		" + maxSplits
							+ "\n5. Wager Limits: 		Min " + minWager + ", Max " + maxWager
							+ "\n6. Soft 17:			" + softSeventeen
							+ "\n7. Default Settings"
							+ "\n8. Return to Menu\n");
			
			int userChoice = getIntFromScannerRanged(scnr, 1, 8);
			
			switch(userChoice) {
				
				case 1: 
					printlnPause("Enter new player name: ");
					playerName = scnr.nextLine();
					System.out.println();
					break;
				
				case 2:
					printlnPause("Enter desired number of decks (Max 8): ");
					numOfDecks = getIntFromScannerRanged(scnr, 1, 8);
					break;
					
				case 3:
					printlnPause("Enter desired number of CPU players (Max 6): ");
					numOfCpuPlayers = getIntFromScannerRanged(scnr, 0, 6);
					break;
				
				case 4:
					printlnPause("Enter desired maximum of splits per hand (Max 10): ");
					maxSplits = getIntFromScannerRanged(scnr, 0, 10);
					break;
				
				case 5:
					printlnPause("Enter minimum wager: (Max 100)");
					minWager = getIntFromScannerRanged(scnr, 0, 100);
					printlnPause("Enter maximum wager: (Between " + minWager + " and 100)");
					maxWager = getIntFromScannerRanged(scnr, minWager, 100);
					break;
					
				case 6: 
					softSeventeen = !softSeventeen;
					printlnPause("Soft 17 is now " + softSeventeen);
					break;
					
				case 7: 
					defaultSettings();
					printlnPause("Settings returned to default Casino Rules");
				
				
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
		
		currentGame = new Game();
		
		currentGame.placeWagers(scnr); // cam
		
		currentGame.dealCards(); // mykel
		
		currentGame.playHands(); // cam

		currentGame.payout(); // cam
		
		currentGame = null;
		
		saveSettings(); // mykel 
		
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
				
					printlnPause("Invalid input, please enter a int between " + low + " and " + high + ".");
			
				}
	    
		} while (userNum < low || userNum > high);
        
        	return userNum;
        
	}
	
// DEBUG METHODS
	
	public static void mykelTest() throws IOException {
		//TODO : test saveSettings() and loadSettings()
		//saveSettings();
		//loadSettings();
	
	}
	
	public static void camTest(Scanner scnr) throws IOException {
		
		currentGame = new Game();
		
		currentGame.getDealer().getHand().addCard(new Card(Rank.TEN, Suit.DIAMONDS));
		currentGame.getDealer().getHand().addCard(new Card(Rank.SEVEN, Suit.DIAMONDS));
		softSeventeen = true;
		
		printlnPause(currentGame.getDealer().getHand().getCard(1).getName() + "\n");
		
		currentGame.playHands();
		
	}
	
	public static void printlnPause(String string) {
		
		for(int i = 0; i < string.length(); i++){
			
		    System.out.printf("%c", string.charAt(i));
		    
		    try {
		    	
		        Thread.sleep(textSpeed);
		        
		    } catch(InterruptedException ex){
		    	
		        Thread.currentThread().interrupt();
		        
		    }
		    
		}
		
		System.out.println();
		
	}
	
}
