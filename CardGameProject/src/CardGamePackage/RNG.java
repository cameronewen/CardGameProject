package CardGamePackage;
import java.util.Random;

public class RNG {
	
	private static String[] firstNames = {"Cameron", "Dawson", "My'kel", "Jack", "Shun", "Joshua", "Micheal", "Mehmet", "Olivia", "Misaki", "Emma", "Maricel", "Zeynep", "Maeve", "Lukas", "Jan", "Magnus", "Rasmus", "Sean", "Francesco", "Tereza", "Ona", "Lucia", "Juan", "Samuel", "Mohamed", "Bilal", "Omar", "Fadi", "Peter", "Mina", "Markos", "Beshoi", "Karim", "Soleil", "Farma", "Maha", "Hasnaa", "Rowan", "Ruth", "Aya", "Farah", "Hana", "Valentina", "Davit", "Wei", "Ori", "Krishna", "Nathaniel", "Tamar", "Aadya", "Jing", "Mariam", "Odval", "Leo", "Luiza", "Trin", "Lizbert", "Alina", "Kate", "Ray", "Autumn", "Ryuichi", "Chihiro", "Reigi"};
	private static String[] lastNames = {"Rodriguez", "Da Silva", "Mamani", "Persaud", "Smith", "Jean", "Charles", "Clarke", "Ivanova", "Khan", "Sok", "Sari", "Devi", "Dela Cruz", "Cohen", "Mohammadi", "John", "Kumar", "Latu", "Meredith", "Ioane", "Abdou", "Ali", "Nkosi", "Rakotomalala", "Kamara", "Jallow", "Martin", "Rossi", "Nowak", "De Jong", "Jensen", "Ewen", "Glass", "Swan", "Hatsune", "Gavin", "Justice", "Edgeworth", "Wright", "Valentine", "Louis", "Bandomere", "Lamda", "Doe", "Hern", "Johnson", "Pip", "Gold", "Deller", "McDaniels", "Lacey-Kinsey", "Hitch", "Fizzlebean", "Mitsurugi", "Naruhodo", "Sato", "Ito", "Watanabe"}; 


	public static boolean randomBoolean() {
		//random Boolean is for CPU logic splitting the deck
		boolean randBoolean = false;
		Random random = new Random();
		int randomIndex = random.nextInt(2);
		
		switch(randomIndex) {

		case 0:
			randBoolean = false;
			break;

		case 1:
			randBoolean = true;
			break;
			
		default:
			System.out.println("Something went wrong with the randBoolean generator.");

		} // end switch
		
		return randBoolean;
		
	}
	
	public static String randomName() {
		
		Random random = new Random();
		String firstName = firstNames[random.nextInt(firstNames.length)];
		String lastName = lastNames[random.nextInt(lastNames.length)];
		
		return firstName + " " + lastName;
		
	}

	public static int cpuMoneyRandom() { // assigns the cpu a cash allowance (used to determine split/dd)
		
		Random rng = new Random();
		int cpuMoney;
		
		cpuMoney = rng.nextInt(2*Main.maxWager); // random number between 0 and 2 times the max wager
		cpuMoney += Main.minWager; // adds min wager to make sure they can wager w/in the rules
		
		return cpuMoney;
		
	}
}
