package CardGamePackage;

public class RNG {
	
	
	public RNG() {
	
	//This is a Random Number Generator used to shuffle the cards in the deck
	// Xsubn-1 = (aXsubn + b) mod m
	}
	
	public static int RandomNumberGenerator(int i) {
		//Test zone open
		
		int x = i%51;
		
		
		return x;
		
		
		
		
		
		//Test zone closed
		
		
	}

	public static boolean randomBoolean() {
		
		boolean randBoolean = false;
		
		// TODO My'kel could you make a method that returns a random boolean?
		// possibly just ask your current random number generator to get a num between 0-1?
		
		return randBoolean;
		
	}
	
	public static String randomName() {
		
		return "John Smith";
		
	}
}
