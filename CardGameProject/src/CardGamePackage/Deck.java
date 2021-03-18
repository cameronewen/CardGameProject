package CardGamePackage;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

// FIELDS

	private ArrayList<Card> cardDeck = new ArrayList<Card>(52); // allocates space for 1 deck arraylist (it will expand on its own if more cards are added)
	private int cardCount = 0;
	
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
			
			temp = cardDeck.get(randomIndex); // the card at random point is saved to temp
			
			cardDeck.set(randomIndex, cardDeck.get(i)); // the card at i is moved to random index
			cardDeck.set(i, temp); // the card previously in randomindex is moved to i
			
		}
		
		cardCount = 0; // resets card count
		
	}
	
	
	// returns the last card on the deck and (-->) REMOVES (<--) it from the deck.
	public Card dealCard() {
		
		Card temp = cardDeck.get(0); // pulls bottom card
		
		cardCount(temp);
		
		cardDeck.remove(0); // removes it from the deck
		
		return temp; // returns bottom card
		
	}
	
	private void cardCount(Card card) {
		
		switch(card.getRank()) {
		
		case TWO:
		case THREE:
		case FOUR:
		case FIVE:
		case SIX:
			
			cardCount++;
			break;
			
		case TEN:
		case JACK:
		case QUEEN:
		case KING:
			
			cardCount--;
			break;
			
		default:
			break;
		}
		
	}
	
// GETTERS AND SETTERS
	
	public int getSize() {
		
		return cardDeck.size();
		
	}
	
	public Card getCardAtIndex(int index) { // does NOT remove a card from the deck
		
		return cardDeck.get(index);
		
	}

	public int getCardCount() {
		return cardCount;
	}
	
}
