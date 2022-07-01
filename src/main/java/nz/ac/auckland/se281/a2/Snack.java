package nz.ac.auckland.se281.a2;

import nz.ac.auckland.se281.a2.cli.Menu.SIZE;

public class Snack extends Food {
	
	public Snack() {
		
	}
	
	public Snack(String name, float price, SIZE size) {
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
	
	public int orderTime(int snackCount) {
		
		if (snackCount < 1) {
			return 0;
		}
		
		// return total time to make snacks in seconds
		int snackTimeInSeconds = 180 + ((snackCount - 1) * 30);
		return snackTimeInSeconds;
	}
}
