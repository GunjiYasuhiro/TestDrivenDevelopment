package chapter04_Privasy;

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
}

/*

インライン化

メソッド呼び出し部をメソッドそのもので置き換えよう

方法
１．対象のメソッドをコピーする
２．メソッド呼び出し部にそのメソッド本文をペーストする
３．仮引数を実引数に置き換える。例えばもしreader.getNext()の結果を渡しているなら、
	副作用を生む式であることに注意してローカル変数に代入する。

private化によって、あるリスクを受け入れたことを理解しなければならない。

等価性比較のテストが、等価性比較が正確に実装されていることを検証できていなかったのならば、
掛け算のテストもきちんと掛け算ができていることを検証できないということ。
これはTDDが立ち向かうべきリスク。

*/
