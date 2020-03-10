package chapter15_MixedCurrencies;

interface Expression {
	Expression plus(Expression addend);
	Money reduce(Bank bank, String to);
}
