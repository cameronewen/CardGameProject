package CardGamePackage;
import java.util.Scanner;

public class Main {
	
	public static void  Menu() {
		System.out.println("Menu:");
		System.out.println("> Gameplay and Rules (1)\n"
				+ "> Deal hand (2)\n" + "> Exit (3)\n");
		
		
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		int menuOption; 
		
		
		System.out.println("Welcome to BlackJack\n");
        
		Menu();
		
		menuOption = scnr.nextInt(); 
		
		if (menuOption == 1) {
			System.out.println("> Gameplay and Rules \n");
			
		} else if (menuOption ==2) {
			System.out.println("Deal hand \n");
			
		} else {
			System.out.println ("Exit \n");
		}
	}
	
	
	
}