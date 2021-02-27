package CardGamePackage;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		Scanner scnr = new Scanner(System.in);
		

		System.out.println("Welcome to BlackJack\n");
        
		Menu(scnr);
		
		scnr.close();
		
	}
	
	// patch note: adjusted menu to use a method to take input to avoid errors
	// and changed the if to a switch statement so we can easily add more options.
	// - cam
	
	public static void Menu(Scanner scnr) {
		
		int menuSelection; 
		
		System.out.println("Menu:");
		System.out.println("> Gameplay and Rules (1)\n"
				 + "> Deal hand (2)\n" 
				 + "> Exit (3)\n");
		
		menuSelection = getIntFromScannerRanged(scnr, 1, 3);
		        
		switch(menuSelection) {

			case 1:
				System.out.println("Gameplay and Rules");
				break;

			case 2:
				System.out.println("Start game.");
				break;

			case 3:
				System.out.println("Exit Game");
				break;

			default:
				System.out.println("Something went wrong with the input scanner.");

		}
        
		
		scnr.close(); 
		
	}
	
	
	
	/* getIntFromScannerRanged by Cam
	 * 
	 * Returns an integer from user input without having to worry about input mismatch
	 * Specifies a range of accepted input, but I have a version without if we need
	 * unregulated user entered integers.
	 * 
	 */

	public static int getIntFromScannerRanged(Scanner scnr, int low, int high) { 
		
		String userInputString;
		int userNum = low - 1; // error indicator.
		
		
		System.out.println("Enter an integer between " + low + " and " + high + ".");
		
		do {
			
			userInputString = scnr.nextLine();
			System.out.println();
			
			try {
				
				userNum = Integer.parseInt(userInputString.replaceAll(" ", ""));
						
			} catch (NumberFormatException e) {
				
				System.out.println("Invalid input, please enter a int between " + low + " and " + high + ".");
				System.out.print(">");
				
			}
	    
		} while (userNum < low || userNum > high);
        
        	return userNum;
        
	}
	
	
	
}
