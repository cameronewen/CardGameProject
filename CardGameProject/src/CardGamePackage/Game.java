package CardGamePackage;

import java.util.ArrayList;

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
		user = new User(Main.playerName, Main.playerMoney);
		
		for(int i = 0; i < Main.numOfPlayers; i++) {
			
			cpuList.add(new CpuPlayer()); // TODO add name randomization 
			// NOTE: name rng method already created in RNG, currently puts out "john smith"
			
		}
		
	}
	
// METHODS
	
	public void placeWagers() {
		
		// players selects wager (add max to settings?) all wagers / returns from here are decided by game rules
		
	}
	
	public void dealCards() {
		
		// deal first card to dealer (hidden) (dealer.getHand().addCard(deck.dealCard())
		// first card to player
		// first card to each cpu (for loop on cpu, if no cpus, for loop wont run)
		
		// repeat for second card
		
		// print each card as it comes out? format being like ?? (Player Name): (Card Title)
		
	}
	
	public void playHands() {
		
		// player first (method should be in user class)
		// then cpus (method wip in cpuPlayer class)
		// then dealer players hand mechinically
		
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
	
}
