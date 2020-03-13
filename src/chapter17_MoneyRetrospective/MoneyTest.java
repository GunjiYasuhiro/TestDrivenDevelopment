package chapter17_MoneyRetrospective;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoneyTest {
	@Test
	public void testMultiplication() {
		Money five = Money.dollar(5);
		assertEquals(Money.dollar(10), five.times(2));
		assertEquals(Money.dollar(15), five.times(3));
	}
	@Test
	public void testEquality() {
		assertTrue(Money.dollar(5).equals(Money.dollar(5)));
		assertFalse(Money.dollar(5).equals(Money.dollar(6)));
		assertFalse(Money.franc(5).equals(Money.dollar(5)));
	}
	@Test
	public void testCurrency() {
		assertEquals("USD", Money.dollar(1).currency());
		assertEquals("CHF", Money.franc(1).currency());
	}
	@Test
	public void testSimpleAddition() {
		Money five = Money.dollar(5);
		Expression sum = five.plus(five);
		Bank bank = new Bank();
		Money reduced = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(10), reduced);
	}
	@Test
	public void testPlusReturnSum() {
		Money five = Money.dollar(5);
		Expression result = five.plus(five);
		Sum sum = (Sum)result;
		assertEquals(five, sum.augend);
		assertEquals(five, sum.addend);
	}
	@Test
	public void testReduceSum() {
		Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
		Bank bank = new Bank();
		Money result = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(7), result);
	}
	@Test
	public void testReduceMoney() {
		Bank bank = new Bank();
		Money result = bank.reduce(Money.dollar(1), "USD");
		assertEquals(Money.dollar(1), result);
	}
	@Test
	public void testReduceMoneyDifferentCurrency() {
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Money result = bank.reduce(Money.franc(3), "USD");
		assertEquals(Money.dollar(1), result);
	}
	@Test
	public void testIdentityRate() {
		assertEquals(1,  new Bank().rate("USD", "USD"));
	}
	@Test
	public void testMixedAddition() {
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
		assertEquals(Money.dollar(10), result);
	}
	@Test
	public void testSumPlusMoney() {
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
		Money result = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(15), result);
	}
	@Test
	public void testSumTimes() {
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
		Money result = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(20), result);
	}
}

/*

□ここから先はどうなるのか
そもそも他国通貨のコードは書き終わったといえるのだろうか？
答えはノー
見えているタスクがすべて終わったら、今度はコードを批判的にみるようにしている。
ツールは何かを忘れたり見逃したりということがない。
もう使われていないコードを消し忘れても、ツールは見逃さずに指摘してくれる。
TODOリストが空の時は設計を見直すいいタイミングになる。
設計の言葉と概念に齟齬はないだろうか。
現在の設計では除去が難しい重複はないだろうか。
長く残っている重複は、新たな設計の必要を示唆していることが多い。

■メタファー
他国通貨サンプルを作成した際の最大の驚きは、設計結果が依然と大きく変わったこと。
正直メタファーがここまで強力だとは思っていなかった。
メタファーは名前の根正に使う程度だと考えていたが、そうではなかった。
メタファーとしてMoneySum→MoneyBug(こちらのほうが物理的な意味でもよかった)
→Wallet(より実生活に近いメタファーだ)を使った。

■JUnit使用状況
・テストコードとプロダクトコードの行数やメソッド数は近いといえる
・テストコードの行数は共通のフィクスチャーを使えばもっと削減できる
・テストコードは条件分岐やループがないので循環的複雑度は１
　プロダクトコードの方も、条件分岐の代わりにポリモフィズムを使っているので、
　複雑度は低く抑えられている
・メソッドの定義の開きカッコと閉じカッコを含む
・テストコードのメソッドあたりの行数が多めなのは、
　先ほど触れた共通フィクスチャーをまだ抽出していないから。

■カバレッジについての指摘
・カバレッジの総量とは、プログラムをさまざまな側面から検証しているテストの数を、
　テストすべき側面の数（ロジックの複雑度と言い換えてもいい）で割ったもの
　より多くのテストを書けばカバレッジは上がる
　だからテスト駆動開発者が書くテスト数と
　テスト専門のエンジニアが書くテスト数には大きな隔たりがある。
・実はカバレッジを上げる方法はもう一つある。
　テスト数をそのままに、プログラムのロジックの方をシンプルにするのだ。
・条件分岐のメッセージへの置き換えや、条件分岐の除去といったリファクタリングは、
　まさにこの作業に該当する。
・「テストを増やすことによってすべての入力の組み合わせをカバーするのではなく、
　同じテストのままで、コードを減らすことによってより多くの組み合わせをカバーするのだ」

最後の振り返り

TDDを教える度、次の3つのことに驚かれる
・テストをきれいに機能させる３つのアプローチ
　・仮実装
　・三角測量
　・明白な実装
・テストとコードの間の重複除去が、設計を駆動する
・テストの間のギャップを制御する能力。
　と麺が滑りやすいならグリップを増し、路面が良いならより早く。





*/