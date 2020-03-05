package chapter08_MakinObjects;

public class Franc extends Money {
	Franc(int amount) {
		this.amount = amount;
	}
	Money times(int multiplier) {
		return new Franc(amount * multiplier);
	}
}
