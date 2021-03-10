package CardGamePackage;
import java.util.Random;

public class RNG {
	
	
	public RNG() {
	
	}
	


	public static boolean randomBoolean() {
		
		boolean randBoolean = false;
		Random random = new Random();
		int randomIndex = random.nextInt(2);
		
		
		
		switch(randomIndex) {

		case 1:
			randBoolean = false;
			break;

		case 2:
			randBoolean = true;
			break;
			
		default:
			System.out.println("Something went wrong with the randBoolean generator.");

	}
		
		
		return randBoolean;
		
	}
	
	public static String randomName() {
		
		// this is for naming CPUs and the dealer eventually, the John Smith thing is filler
		// so it works in the meantime
		
		return "John Smith";
		
	}
}
