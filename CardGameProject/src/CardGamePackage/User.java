package CardGamePackage;

public class User extends Player{
	
	public int totalMoney;

	public User(String name, int money) {
		
		super(name);
		this.totalMoney = money;
		
	}
	
	public User(String name) {
		
		super(name);
		this.totalMoney = 100;
		
	}

}
