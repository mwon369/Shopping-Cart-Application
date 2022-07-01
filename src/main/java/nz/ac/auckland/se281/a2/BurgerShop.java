package nz.ac.auckland.se281.a2;

import java.util.ArrayList;

import nz.ac.auckland.se281.a2.cli.Menu.SIZE;
import nz.ac.auckland.se281.a2.cli.MessagesCLI;

public class BurgerShop {
	
	// instance fields to act as the cart
	private ArrayList<Food> cart = new ArrayList<Food>();
	private int cartPos;
	private float cartPrice;

	public BurgerShop() {

	}

	/**
	 * Add a burger in the cart
	 * 
	 * @param name
	 * @param price
	 */
	public void addBurger(String name, float price) {
		// TODO TASK1
		
		// construct burger instance and add to cart
		Burger burger = new Burger(name, price);
		cart.add(burger);
	}

	/**
	 * add a snack in the cart, if size is L the price should be incremented by $3
	 * if the size is XL the price should be incremented by $4 (@see
	 * nz.ac.auckland.se281.a2.cli.Menu.SIZE)
	 * 
	 * 
	 * @param name
	 * @param price
	 * @param size
	 */
	public void addSnack(String name, float price, SIZE size) {
		// TODO TASK1

		// construct snack instance and add to cart
		Snack snack = new Snack(name, price, size);
		cart.add(snack);
	}

	/**
	 * 
	 * add a drink in the cart
	 * 
	 * if size is L the price should be incremented by $3 if the size is XL the
	 * price should be incremented by $4 (@see
	 * nz.ac.auckland.se281.a2.cli.Menu.SIZE)
	 * 
	 *
	 * @param name
	 * @param price
	 * @param size
	 */
	public void addDrink(String name, float price, SIZE size) {
		// TODO TASK1

		// construct drink instance and add to cart
		Drink drink = new Drink(name, price, size);
		cart.add(drink);
	}

	/**
	 * print the content of the cart, or print MessagesCLI.CART_EMPTY if the cart is
	 * empty
	 *
	 *
	 */
	public void showCart() {
		// TODO TASK1
		
		// check for empty cart
		if (cart.size() < 1) {
			MessagesCLI.CART_EMPTY.printMessage();
			return;
		}
		
		cartPos = 0;
		cartPrice = 0;
		
		// print each item with its correct cart position and calculate total cart price
		for (Food item : cart) {
			System.out.println(cartPos++ + " - " + item.name + ": $" + String.format("%.02f", item.price));
			cartPrice += item.price;
		}
		
		// apply 25% discount for total cart prices >= $100
		if (cartPrice >= 100) {
			cartPrice *= 0.75;
			MessagesCLI.DISCOUNT.printMessage();
		}
		
		// print total with formatted string
		System.out.println("Total: $" + String.format("%.02f", cartPrice));
	}

	/**
	 * add a combo in the cart.
	 * 
	 * The price of a combo is the sum of all the items, but the drink would be half
	 * price. Note that in a combo, both snacks and drinks have the same size, and
	 * the combo price must consider the size (@see addSnack and addDrink methods).
	 * 
	 * @param nameBurger
	 * @param priceBurger
	 * @param nameSnack
	 * @param priceSnack
	 * @param nameDrink
	 * @param priceDrink
	 * @param size
	 */
	public void addCombo(String nameBurger, float priceBurger, String nameSnack, float priceSnack, String nameDrink,
			float priceDrink, SIZE size) {
		// TODO TASK2
		
		// construct instances of all food items
		Burger burger = new Burger(nameBurger, priceBurger);
		Snack snack = new Snack(nameSnack, priceSnack, size);
		Drink drink = new Drink(nameDrink, priceDrink, size);
		
		// construct combo instance and add to cart
		Combo combo = new Combo(burger, snack, drink);
		cart.add(combo);
	}

	/**
	 * remove the item in the cart specified by the position posCart. Note that the
	 * position of the cart start from zero. if postCart is invalid: posCart < 0 OR
	 * posCart >= size of the cart the method prints
	 * MessagesCLI.NOT_VALID_CART_POSITION
	 * 
	 * @param posCart
	 */
	public void removeItem(int posCart) {
		// TODO TASK3
		
		// check if cart position is valid
		if (posCart < 0 || posCart > cart.size() - 1) {
			MessagesCLI.NOT_VALID_CART_POSITION.printMessage();
			return;
		}
		
		// remove method will shift all subsequent items in the cart up by one automatically
		cart.remove(posCart);
	}

	/**
	 * removes all elements in the cart
	 */
	public void clearCart() {
		// TODO TASK3

		cart.clear();
	}

	/**
	 * This method confirms the order, showing the estimated pick up time. It also
	 * give a warning if there are missing opportunities for COMBO menus
	 * 
	 */
	public void confirmOrder() {
		// TODO TASK4
		
		// check for empty cart
		if (cart.size() < 1) {
			MessagesCLI.ORDER_INVALID_CART_EMPTY.printMessage();
			return;
		}
		
		// check for potential combos
		Combo combo = new Combo();
		boolean potentialCombo = combo.isPossibleCombo(cart);
		
		if (potentialCombo) {
			MessagesCLI.MISSED_COMBO.printMessage();
			return;
		}
		
		Burger burger = new Burger();
		Snack snack = new Snack();
		Drink drink = new Drink();
		
		// count how many burgers, snacks and drinks are in the cart
		int burgerCount = burger.countFoodTypes(cart);
		int snackCount = snack.countFoodTypes(cart);
		int drinkCount = drink.countFoodTypes(cart);
		
		// compute how much time the order will take to make
		int orderTimeInSeconds = burger.orderTime(burgerCount) + snack.orderTime(snackCount) + drink.orderTime(drinkCount);
		int hours = orderTimeInSeconds / 3600;
		int minutes = (orderTimeInSeconds % 3600) / 60;
		int seconds = orderTimeInSeconds % 60;
		
		showCart();
		
		// print total time with formatted string
		String time = hours + " hours " + minutes + " minutes " + seconds + " seconds";
		System.out.println(MessagesCLI.ESTIMATE_WAITING_TIME.getMessage() + time);
		
		clearCart();
	}
}
