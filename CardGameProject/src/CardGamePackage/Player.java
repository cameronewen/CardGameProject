package CardGamePackage;

import java.util.ArrayList;

public class Player { // this is not the user, this class includes npcs

// FIELDS
	
	protected ArrayList<Hand> playerHands = new ArrayList<Hand>(); // players can have multiple hands thanks to splitting
	protected String name;
	protected double money;
	protected int winnings;

	
// CONSTRUCTOR
	
	public Player(String name, double playerMoney) {
		
		playerHands.add(new Hand());
		this.name = name;
		this.money = playerMoney;
		
	}
	
// METHODS
	
	public void splitHand(Hand handToSplit) {
		// if the hands has only two cards AND the hand has been split less than twice AND the cards are equal
		if( (handToSplit.getSize() == 2) && 
			(handToSplit.getTimesSplit() < Main.maxSplits) &&
			(money >= handToSplit.getWager()) &&
			( 	(handToSplit.getCard(0).getValue() 	== handToSplit.getCard(1).getValue()) || 
				(handToSplit.getCard(0).getRank() 	== handToSplit.getCard(1).getRank())  )){
			
			Hand newHand = new Hand(handToSplit.getCard(1)); // add 2nd card to its own new hand
			handToSplit.removeCard(1); // remove card from source hand
			
			handToSplit.setTimesSplit(handToSplit.getTimesSplit() + 1); // incrementing the times a hand has been split
			newHand.setTimesSplit(newHand.getTimesSplit() + 1);
			
			handToSplit.addCard(Main.currentGame.getDeck().dealCard()); // adds a new card to each hand from the deck
			newHand.addCard(Main.currentGame.getDeck().dealCard()); 
			
			money -= handToSplit.getWager();
			
			newHand.setWager(handToSplit.getWager()); // sets wager of new hand equal to first hand
			
			playerHands.add(newHand); // add the new hand to the players hands
			
		} else {
			
			Main.printlnPause("DEBUG: You can't split this hand!"); // this shouldn't happen in game, option shouldn't be offered if they can't split
			
			if( !(handToSplit.getTimesSplit() < Main.maxSplits) ) {
				
				Main.printlnPause("DEBUG: handToSplit.getTimesSplit() < Main.maxSplits");
				
			}
			
			if(handToSplit.getCard(0).getValue() != handToSplit.getCard(1).getValue()) {
				Main.printlnPause("DEBUG: card 1 doesnt have same value as card 2");
			}
			
		}
		
	}

	public void stand(Hand handToStand) {
		handToStand.setHasBeenStoodOn(true);
	}
	
	public void surrender(Hand handToSurrender) {
		handToSurrender.setSurrendered(true);
		handToSurrender.setActive(false);
		//Main.printlnPause("DEBUG: give player back 1/2 wager");
	}
	
	public int calculateWinnings() {
		
		int winnings = 0;
		Hand currHand;
		Hand dealerHand = Main.currentGame.getDealer().getHand();
		
		for(int i = 0; i < playerHands.size(); i++) {
			
			currHand = playerHands.get(i);
			
			if(currHand.isBlackjack() && !dealerHand.isBlackjack()) {
				
				winnings += currHand.getWager() * 1.5; //blackjack
				
			} else if(currHand.isBlackjack()) {
				
				winnings += currHand.getWager(); // blackjack but dealer got 21
				
			} else if(currHand.getSurrendered()) {
				
				winnings += currHand.getWager()/2.0; // 
				
			} else if(!currHand.isBusted() && currHand.getValue() > dealerHand.getValue()) {
				
				winnings += currHand.getWager() * 1.5;
				
			} else if(dealerHand.isBusted() && currHand.isActive()) {
				
				winnings += currHand.getWager();
				
			} else if(!currHand.isBusted() && currHand.getValue() == dealerHand.getValue()) {
				
				winnings += currHand.getWager();
			}
			
		}
		
		
		return winnings;
	}
	
// GETTERS AND SETTERS
	
	public String getName() {
		return name;
	}

	public void setName(String playerName) {
		this.name = playerName;
	}
	
	public Hand getHand(int i) {
		return playerHands.get(i);
	}
	
	public Card getCardFromHand(int handIndex, int cardIndex) {
		return playerHands.get(handIndex).getCard(cardIndex);
	}
	
	public int getPlayerHandsSize() {
		return playerHands.size();
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		
		this.money = money;
		
	}
}
