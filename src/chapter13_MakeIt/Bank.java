package chapter13_MakeIt;

public class Bank {
	Money reduce(Expression source, String to) {
		return source.reduce(to);
	}
}
