package CardGamePackage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
// FIELDS

	private Dealer dealer;
	private Deck deck; 
	private User user;
	private ArrayList<CpuPlayer> cpuList = new ArrayList<CpuPlayer>();
	
// CONSTRUCTORS
	
	public Game() { 
		dealer = new Dealer();  
		deck = new Deck(Main.numOfDecks);
		deck.shuffleDeck();
		user = new User(Main.playerMoney);
		
		for(int i = 0; i < Main.numOfCpuPlayers; i++) {
			
			cpuList.add(new CpuPlayer()); 
			
		}
		
	}
	
// METHODS
	
	public void placeWagers(Scanner scnr) {
		
		Random rng = new Random();
		
		Main.printlnPause("Wagers\n\nMinimum Wager: $" + Main.minWager + "\nMaximum Wager: $" + Main.maxWager);
		Main.printlnPause("\nCurrent Wallet: $" + user.getMoney() + "\n");
		
	
		if(Main.playerMoney < Main.minWager) { // if they dont have enough to make min bet, add min wager to their account.
			
			Main.printlnPause("You don't have enough money to bet, have $" + Main.minWager + " on us.");
			
			user.setMoney(user.getMoney() + Main.minWager); // gives player minimum wager amount
			Main.printlnPause("\nCurrent Wallet: $" + user.getMoney() + "\n");
			
		}
		
		Main.printlnPause("Place a wager on this game: ");

		user.getHand(0).setWager(Main.getIntFromScannerRanged(scnr, Main.minWager, Main.maxWager)); // asks for wager between min / max
		
		if(user.getHand(0).getWager() > user.getMoney()) { // if wager is greater than the money they have, get num between min and player money
			
			Main.printlnPause("Slow down there, you don't have enough cash for that bet.");
			user.getHand(0).setWager(Main.getIntFromScannerRanged(scnr, Main.minWager, user.getMoney()));
			
		}
		
		user.setMoney(user.getMoney() - user.getHand(0).getWager());

		Main.printlnPause("Current Wallet: $" + user.getMoney() + "\n");
		
		Main.printlnPause(Main.playerName + " wagered $" + user.getHand(0).getWager() + "\n");
		
		for(int i = 0; i < cpuList.size(); i++) { // start cpu wagers
			
			int wager;
			
			do {
				
				wager = rng.nextInt((Main.maxWager - Main.minWager)) + Main.minWager;
				
			} while(wager > cpuList.get(i).getMoney());
			
			
			
			cpuList.get(i).getHand(0).setWager(wager);
			//Main.printlnPause("This cpu has $" + cpuList.get(i).getMoney());
			Main.printlnPause(cpuList.get(i).getName() + " wagered $" + wager + "\n");
			
			cpuList.get(i).setMoney(cpuList.get(i).getMoney() - wager);
			
		} // end cpu wagers
		
		Main.playerMoney = user.getMoney();
	}
	
	public void dealCards() { //my'kel (w cameron's help}
		
		// DEALER
		Main.printlnPause("\n\nDealing\n");
		
      	for(int j = 0; j < 2; j++) {
    	  
			dealer.getHand().addCard(deck.dealCard());
			
			if(j == 1) {
				  
				Main.printlnPause(dealer.getUpcard().getName() + "\n");
				  
			} else {
				  
				Main.printlnPause(dealer.getName() + " places their first card face down.");
				  
			}
    	  
      	} // end dealer for loop
      	
      	
      	// USER
      
		Main.printlnPause("You're starting hand:\n");
		      
		for(int j = 0; j < 2; j++) { // deal user two cards, print each
		    	  
			user.getHand(0).addCard(deck.dealCard());
			Main.printlnPause(user.getHand(0).getCard(j).getName());
		    	  
		}
      
     	System.out.println();
     	
     	
     	// CPUs
     
		for(int i = 0; i < (cpuList.size()); i++) {	// for loop of cpus
			
             Main.printlnPause(cpuList.get(i).getName() + "'s starting hand:");
             System.out.println();
             
             for(int k = 0; k < 2; k++) { // deals 2 cards to each cpu, prints out the cards
			  
				 cpuList.get(i).getHand(0).addCard(deck.dealCard()); // deal card
				 Main.printlnPause(cpuList.get(i).getHand(0).getCard(k).getName()); // print card
				 
             } // end interior for loop
             
             System.out.println();
             
         } // end cpu for loop
		
		System.out.println();
		
	}
	
	public void playHands() { // TODO
		
		// player first (method should be in user class)
		
		for(int i = 0; i < cpuList.size(); i++) {
			
			cpuList.get(i).playHandCPU();
			
		}
		
		dealer.playHand();
		
		
		
	}
	
	public void payout() {
		// announce players winnings if theyve made it this far / and cpu winnings?
	}
	
	
// GETTERS AND SETTERS
	
	public Dealer getDealer() {
		return dealer;
	}
	
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void addCpu(CpuPlayer newCpu) {
		cpuList.add(newCpu);
	}
	
	public CpuPlayer getCpu(int index) {
		return cpuList.get(index);
	}
	
	public void clearCpuList() {
		cpuList.clear();
	}

	public void release() {
		// TODO Auto-generated method stub
		
	}
	
}
