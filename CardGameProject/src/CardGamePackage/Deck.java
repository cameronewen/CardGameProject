package CardGamePackage;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

// FIELDS
	
	private final int STANDARD_DECK_SIZE = 52;
	private ArrayList<Card> cardDeck = new ArrayList<Card>(52); // allocates space for 1 deck arraylist (it will expand on its own if more cards are added)

	
// CONSTRUCTORS
	
	// single standardized deck
	public Deck() {
		
		standardizeDeck();
		
	}
	
	// adds additional standard decks. (argument of 1 == 1 standard deck)
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
		
		for (Suit suit : Suit.values()) { // for loop that iterates through an enum
			
		    for (Rank rank : Rank.values()) {
		    	
		         Card card = new Card(rank, suit);
		         cardDeck.add(card);
		         
		    } 
		    
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
	
	
	public void shuffleDeck() {
	
		
		Card temp;
		int randomIndex;
		
		Random random = new Random();
		
		for(int i = 0; i < cardDeck.size(); i++) {
			
			randomIndex = random.nextInt(cardDeck.size()); // rand index is chosen
			System.out.print(i + ": "); // debug
			System.out.println(randomIndex); // debug 
			temp = cardDeck.get(randomIndex); // the card at random point is saved to temp
			
			cardDeck.set(randomIndex, cardDeck.get(i)); // the card at i is moved to random index
			cardDeck.set(i, temp); // the card previously in randomindex is moved to i
			
		}
		
	}
	
	
	// returns the last card on the deck and (-->) REMOVES (<--) it from the deck.
	public Card dealCard() {
		
		Card temp = cardDeck.get(0); // pulls bottom card
		
		cardDeck.remove(0); // removes it from the deck
		
		return temp; // returns bottom card
		
	}
	
	
// GETTERS AND SETTERS
	
	public int getDeckSize() {
		
		return cardDeck.size();
		
	}
	
	public Card getCardAtIndex(int index) { // does NOT remove a card from the deck
		
		return cardDeck.get(index);
		
	}
	
}
