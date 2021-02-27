package CardGamePackage;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

// FIELDS
	
	private final int STANDARD_DECK_SIZE = 52;
	private ArrayList<Card> cardDeck = new ArrayList<Card>(STANDARD_DECK_SIZE);

	
// CONSTRUCTORS
	
	// single standardized deck
	public Deck() {
		
		standardizeDeck();
		
	}
	
	//adds additional standard decks. (argument of 1 == 1 standard deck)
	public Deck(int numOfDecks) {
		
		standardizeDeck();
		
		for(int i = 1; i < numOfDecks; i++) {
			
			addAnotherDeck();
			
		}
		
	}

// DECK METHODS
	
	//resets / sets deck to a standard size and order
	public void standardizeDeck() {
		
		cardDeck.clear();
		
		int i = 0;
		
		for (Suit suit : Suit.values()) { // for loop that iterates through an enum
			
		    for (Rank rank : Rank.values()) {
		    	
		         Card card = new Card(rank, suit);
		         cardDeck.add(card);
		         
		    } 
		    
		}
		
		if(cardDeck.size() > STANDARD_DECK_SIZE) {
			cardDeck.subList(STANDARD_DECK_SIZE, cardDeck.size()).clear();
		}
			
	}
	
	
	// add another standardized deck of cards to the end of the current deck, in order
	public void addAnotherDeck() {
		
		for (Suit suit : Suit.values()) { // for loop that iterates through an enum
			
		    for (Rank rank : Rank.values()) {
		    	
		         Card card = new Card(rank, suit);
		         cardDeck.add(card);
		         
		    } 
		    
		}
		
	}
	
	
	//shuffles the deck randomly. currently uses java.util.random but if we want to use mod we can change it
	public void shuffleDeck() {
		
		Card temp;
		int randomIndex;
		
		Random rng = new Random();
		
		for(int i = 0; i < cardDeck.size(); i++) {
			
			randomIndex = rng.nextInt(cardDeck.size()); // random point is chosen
			temp = cardDeck.get(randomIndex); // the current index of the random point is saved
			cardDeck.set(randomIndex, cardDeck.get(i)); // the 
			cardDeck.set(i, temp); //sets i to the random index we saved earlierS 
			
		}
		
	}
	
	
	// returns the last card on the deck and removes it from the deck.
	public Card dealCard() {
		
		Card temp = cardDeck.get(0); // pulls bottom card
		
		cardDeck.remove(0); // removes it from the deck
		
		return temp; // returns bottom card
		
	}
	
	
// GETTERS AND SETTERS
	
	public int getDeckSize() {
		
		return cardDeck.size();
		
	}
	
	public Card getCardAtIndex(int index) {
		
		return cardDeck.get(index);
		
	}
	
	
	
	
}
