package CardGamePackage;

public class User extends Player{

	public User(int money) {
		
		super(Main.playerName + " (You)", money);
		
	}
	
	public User() {
		
		super(Main.playerName + " (You)", Main.playerMoney);
		
	}
	
	public void playHand() {
		
		// TODO add player moves
		// prints "It's Your Turn" or something
		// reprints dealers upcard
		// prints players current hand / total value ? 
		
		// for loop of players hands (for splitting)
			// inner while loop checking if they've stood / doubled down / surrendered their hand
				// list of options, plus or minus split / (double down on/off in settings?)
				// player selects options, if they split or hit, loop through again, otherwise break loop
			// end while loop
		// end for loop
			
	}

}
