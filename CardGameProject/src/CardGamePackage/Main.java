package CardGamePackage;
import java.util.Scanner;

public class Main {
	
	public static void  Menu() {
		Scanner scnr = new Scanner(System.in);
		int menuOption; 
		
		System.out.println("Menu:");
		System.out.println("> Gameplay and Rules (1)\n"
				+ "> Deal hand (2)\n" + "> Exit (3)\n");
		
        menuOption = scnr.nextInt(); 
		
		if (menuOption == 1) {
			System.out.println("> Gameplay and Rules \n");
			
		} else if (menuOption ==2) {
			System.out.println("Deal hand \n");
			
		} else {
			System.out.println ("Exit \n");
		}
		
		scnr.close(); 
		
	}

	public static void main(String[] args) {
	
		
		
		System.out.println("Welcome to BlackJack\n");
        
		Menu();
	
	}
	
	
	
}