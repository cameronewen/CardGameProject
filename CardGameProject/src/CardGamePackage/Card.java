package CardGamePackage;

public class Card {

// Fields 
	
	private Rank rank;
	private Suit suit;
	private int value;
	private String name; // just for ease get the suit and rank of a card
	
// Card Constructor
	
	public Card(Rank rank, Suit suit) {
		
		this.suit = suit;
		this.rank = rank;
		this.name = rank + " OF " + suit;
		this.value = valueFromRank(rank);
		
	}

// Card Methods
	
	private int valueFromRank(Rank rank) {
		
		int cardValue;
		
		switch(rank) {
		case ACE:
			cardValue = 11;
			break;
		case TWO:
			cardValue = 2;
			break;
		case THREE:
			cardValue = 3;
			break;
		case FOUR:
			cardValue = 4;
			break;
		case FIVE:
			cardValue = 5;
			break;
		case SIX:
			cardValue = 6;
			break;
		case SEVEN:
			cardValue = 7;
			break;
		case EIGHT:
			cardValue = 8;
			break;
		case NINE:
			cardValue = 9;
			break;
		case TEN: // these will all spill over to cardValue = 10 bc there is no break.
		case JACK:
		case QUEEN:
		case KING:
			cardValue = 10;
			break;
		default:
			cardValue = -1;
			System.out.println("Something went wrong with Card.valueFromRank()");
		}
		
		return cardValue;
	}
	
// Getters and Setters
	
	// Rank
	
	public Rank getRank() {
		return rank;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	// Suit
	
	public Suit getSuit() {
		return suit;
	}
	
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	// Value
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	// Name
	
	public String getName() {
		return name;
	}
	
}
