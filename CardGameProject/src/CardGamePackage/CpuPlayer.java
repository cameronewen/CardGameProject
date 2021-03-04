package CardGamePackage;

public class CpuPlayer extends Player{

	public CpuPlayer(String name) {
		
		super(name);
		
	}

	public void playHandCPU() {
		
		// Implement basic strategy
		for(int i = 0; i < playerHands.size(); i++) { // cycles through hands
			
			Hand currHand = playerHands.get(i);
			
			//if statement? check if current hand is active and not 21
			
			do {
			// check if they can and should split for curr hand
				// if yes, split this hand, should continue w/ first card + new card
			// check if they should stand
			// check if they should double down
			// check if they should surrender
			// check if they should hit
				
			} while (!currHand.hasBeenStoodOn()); // while player hasn't stood on hand
		
		}
		
	}
	
}
