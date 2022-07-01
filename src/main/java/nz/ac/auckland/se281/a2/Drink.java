package nz.ac.auckland.se281.a2;

import nz.ac.auckland.se281.a2.cli.Menu.SIZE;

public class Drink extends Food {
	
	public Drink() {
		
	}
	
	public Drink(String name, float price, SIZE size) {
		super(name, price);
		
		/* concatenate the size to the instance field 'name'
		 * and also change the price accordingly
		 */
		switch (size) {
		case M:
			this.name += " (M)";
			break;
		case L:
			this.price += 3;
			this.name += " (L)";
			break;
		case XL:
			this.price += 4;
			this.name += " (XL)";
			break;
		}
	}
	
	public int orderTime(int drinkCount) {
		
		if (drinkCount < 1) {
			return 0;
		}
		
		// return total time to make drinks in seconds
		int drinkTimeInSeconds = 45 + ((drinkCount - 1) * 15);
		return drinkTimeInSeconds;
	}
}
