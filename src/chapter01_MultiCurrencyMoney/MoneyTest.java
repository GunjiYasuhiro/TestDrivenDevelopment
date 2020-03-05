package chapter01_MultiCurrencyMoney;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoneyTest {

	@Test
	void testMultiplication() {
		Dollar five = new Dollar(5);
		five.times(2);
		assertEquals(10, five.amount);
	}

}

/*

TODOリスト
・$5+10 CHF = $10 (レートが2:1の場合)
・$5*2=10
・amountをprivateにする
・Dollarの副作用をどうする？
・Moneyの丸め処理をどうする？

4つのコンパイルエラーがある
・Dollarクラスがない
・コンストラクタがない
・timesメソッドがない
・amountフィールドがない

*/

/*

プログラミング上の課題は
「多国籍通貨対応したい」
から
「このテストを通す」
となり、とてもシンプルになった。

*/

/*

依存性と重複
Steve Freeman
テストコードとプロダクトコードの間にある重複が問題なのではない
問題は、一方を変えたらもう一方も変えないければならないような
プロダクトコードとテストコードの間の依存関係

ゴールは、実装を変えずに「意味のある」テスト
つまり、現状の実装は動かないようなテストをもう１つ書くこと


*/

