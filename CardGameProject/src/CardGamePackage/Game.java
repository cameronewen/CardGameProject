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
		user = new User(Main.playerMoney);
		
		for(int i = 0; i < Main.numOfCpuPlayers; i++) {
			
			cpuList.add(new CpuPlayer()); // TODO add name randomization 
			// NOTE: name rng method already created in RNG, currently puts out "john smith"
			
		}
		
	}
	
// METHODS
	
	public void placeWagers(Scanner scnr) {
		
		Random rng = new Random();
		
		System.out.println("Wagers\n\nMinimum Wager: $" + Main.minWager + "\nMaximum Wager: $" + Main.maxWager);
		System.out.println("\nCurrent Wallet: $" + user.getMoney() + "\n");
		
	
		if(Main.playerMoney < Main.minWager) { // if they dont have enough to make min bet, add min wager to their account.
			
			System.out.println("You don't have enough money to bet, have $" + Main.minWager + " on us.");
			
			user.setMoney(user.getMoney() + Main.minWager); // gives player minimum wager amount
			System.out.println("\nCurrent Wallet: $" + user.getMoney() + "\n");
			
		}
		
		System.out.println("Place a wager on this game: ");

		user.getHand(0).setWager(Main.getIntFromScannerRanged(scnr, Main.minWager, Main.maxWager)); // asks for wager between min / max
		
		if(user.getHand(0).getWager() > user.getMoney()) { // if wager is greater than the money they have, get num between min and player money
			
			System.out.println("Slow down there, you don't have enough cash for that bet.");
			user.getHand(0).setWager(Main.getIntFromScannerRanged(scnr, Main.minWager, user.getMoney()));
			
		}
		
		user.setMoney(user.getMoney() - user.getHand(0).getWager());

		System.out.println("Current Wallet: $" + user.getMoney() + "\n");
		
		System.out.println(Main.playerName + " wagered $" + user.getHand(0).getWager() + "\n");
		
		for(int i = 0; i < cpuList.size(); i++) { // start cpu wagers
			
			int wager;
			
			do {
				
				wager = rng.nextInt((Main.maxWager - Main.minWager)) + Main.minWager;
				
			} while(wager > cpuList.get(i).getMoney());
			
			
			
			cpuList.get(i).getHand(0).setWager(wager);
			//System.out.println("This cpu has $" + cpuList.get(i).getMoney());
			System.out.println(cpuList.get(i).getName() + " wagered $" + wager + "\n");
			
			cpuList.get(i).setMoney(cpuList.get(i).getMoney() - wager);
			
		} // end cpu wagers
		
		Main.playerMoney = user.getMoney();
	}
	
	public void dealCards() { //TODO deal cards to all players ( have an arraylist of cpuplayers thats cycled through?) (User -> Any CPUs -> Dealer)
		
		deck.shuffleDeck(); // note from cam: changed your setup bc theres a deck field created by the constructor just called deck.
		
		
		//Test zone open
		
           
		
		
		//Test zone closed
		
		
		
		// print cards of players as they're dealed, except for dealer, whose upcard is printe
		
		// - Mykel
		
		// deal first card to dealer (hidden) (dealer.getHand().addCard(deck.dealCard())
		// first card to player
		// first card to each cpu (for loop on cpu, if no cpus, for loop wont run)
		
		// repeat for second card
		
		// print each card as it comes out? format being like ?? (Player Name): (Card Title)
		
	}
	
	public void playHands() { // TODO
		
		// player first (method should be in user class)
		// then cpus (method wip in cpuPlayer class)
		// then dealer players hand mechinically
		
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
