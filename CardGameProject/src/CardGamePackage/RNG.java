package CardGamePackage;

public class RNG {
	
	
	public RNG() {
	
	//This is a Random Number Generator used to shuffle the cards in the deck
		//I'm considering adding a method "Timekey() that changes the long from timeMillis into an int and
		//then the timeMillis can be used as key for any randomNumberGenerator, including the boolean 
		//for CPUplayers
		
	// Xsubn-1 = (aXsubn + b) mod m
	}
	
	public static int RandomNumberGenerator(long Timekey) {
	
	    System.out.println("Timekey = " + Timekey);
		int x = (int) Timekey;
		
		
		x = x%51;
		
		System.out.println("RNG TM mod 51 = " + x);
		
		
		return x;
		
		
		
	}

	public static boolean randomBoolean() {
		
		boolean randBoolean = false;
		
		// TODO My'kel could you make a method that returns a random boolean?
		// possibly just ask your current random number generator to get a num between 0-1?
		
		return randBoolean;
		
	}
	
	public static String randomName() {
		//What's this for? [*my'k comment]
		return "John Smith";
		
	}
}
