package nz.ac.auckland.se281.a2;

public class Burger extends Food {
	
	public Burger() {
		
	}
	
	public Burger(String name, float price) {
		super(name, price);
	}
	
	public int orderTime(int burgerCount) {
		
		if (burgerCount < 1) {
			return 0;
		}
		
		// return total time to make burgers in seconds
		int burgerTimeInSeconds = 300 + ((burgerCount - 1) * 60);
		return burgerTimeInSeconds;
	}
}
