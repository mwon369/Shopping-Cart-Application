package nz.ac.auckland.se281.a2;

import java.util.ArrayList;

public class Combo extends Food {
	
	private String combo = "COMBO : (";
	
	public Combo() {
		
	}
	
	public Combo(Burger burger, Snack snack, Drink drink) {
		super(burger.name + ", " + snack.name + ", " + drink.name + ")", 
				burger.price + snack.price + drink.price / 2);
		
		// prepend the combo string to the beginning of the name field
		this.name = combo + this.name;
	}

	public boolean isPossibleCombo(ArrayList<Food> cart) {
		
		ArrayList<String> singleSnacks = new ArrayList<String>();
		ArrayList<String> singleDrinks = new ArrayList<String>();
		
		boolean singleBurger = false;
		boolean snackDrinkSameSize = false;
		
		
		/*  use a loop to check each items instance. If the instance
		 *  is a burger then we have at least one single burger
		 *  
		 *  if the instance is a snack or drink then assign its name 
		 *  to a list of strings so we can check for snacks and drinks
		 *  of the same size later
		 */
		for (Food item : cart) {
			if (item.getClass() == Burger.class) {
				singleBurger = true;
			} else if (item.getClass() == Snack.class) {
				singleSnacks.add(item.name);
			} else if (item.getClass() == Drink.class) {
				singleDrinks.add(item.name);
			}
		}
		
		// check if any given snack name shares the same size postfix as any given drink name
		for (String snackName : singleSnacks) {
			for (String drinkName : singleDrinks) {
				if (snackName.endsWith("(M)") && drinkName.endsWith("(M)")) {
					snackDrinkSameSize = true;
				} else if (snackName.endsWith("(L)") && drinkName.endsWith("(L)")) {
					snackDrinkSameSize = true;
				} else if (snackName.endsWith("(XL)") && drinkName.endsWith("(XL)")) {
					snackDrinkSameSize = true;
				}
			}
		}
		
		return singleBurger && snackDrinkSameSize;
	}

}
