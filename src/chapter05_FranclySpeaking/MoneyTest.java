package chapter05_FranclySpeaking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoneyTest {
	@Test
	public void testMultiplication() {
		Dollar five = new Dollar(5);
		assertEquals(new Dollar(10), five.times(2));
		assertEquals(new Dollar(15), five.times(3));
	}
	@Test
	public void testEquality() {
		assertTrue(new Dollar(5).equals(new Dollar(5)));
		assertFalse(new Dollar(5).equals(new Dollar(6)));
	}
	@Test
	public void testFrancMultiplication() {
		Franc five = new Franc(5);
		assertEquals(new Franc(10), five.times(2));
		assertEquals(new Franc(15), five.times(3));
	}
}

/*

サイクルのおさらい

１．テストを書く
２．コンパイラを通す
３．テストを走らせ、失敗を確認する
４．テストを通す
５．重複を排除する

1.2.3までは速度優先で、良い設計の原則を無視してもよい。
DollerクラスをコピペしてFrancクラスを作成しても構わない。
ただし、設計なんてどうでもよいという免罪符ではない。

*/