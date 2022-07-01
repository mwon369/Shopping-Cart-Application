package nz.ac.auckland.se281.a2;

import java.util.ArrayList;

public abstract class Food {
	
	protected String name;
	protected float price;
	
	public Food() {
		
	}
	
	public Food(String name, float price) {
		this.name = name;
		this.price = price;
	}
	
	public int countFoodTypes(ArrayList<Food> cart) {
		
		int foodTypeCount = 0;
		
		/*  loop to check and count how many of each type
		 *  of food instance we have in our cart, i.e.,
		 *  burgers, snacks and drinks.
		 */
		for (Food item : cart) {
			if (item.getClass() == this.getClass()) {
				foodTypeCount++;
			} else if (item.getClass() == Combo.class) {
				foodTypeCount++; 
			}
		}
		
		return foodTypeCount;
	}
}
