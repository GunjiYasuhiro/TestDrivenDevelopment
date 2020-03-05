package chapter01_MultiCurrencyMoney;

public class Dollar {
	int amount;

	Dollar(int amount) {
		// コンストラクタ定義も必要だが、今はコンパイルを通すことが主眼なので、中身は空でよい
		// →このことを空実装と呼ぶ
		this.amount = amount;
	}

	void times(int multiplier) {
		this.amount *= multiplier;
	}
}
