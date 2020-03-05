package chapter03_EqualityForAll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoneyTest {
	@Test
	public void testMultiplication() {
		Dollar five = new Dollar(5);
		Dollar product = five.times(2);
		assertEquals(10, product.amount);
		product = five.times(3);
		assertEquals(15, product.amount);
	}
	@Test
	public void testEquality() {
		assertTrue(new Dollar(5).equals(new Dollar(5)));
		assertFalse(new Dollar(5).equals(new Dollar(6)));
	}
}

/*

三角測量をまねてコードを一般化できるのは、
２つ以上の実例があるときだけ。
テストコードとプロダクトコードの間の重複は無視してよいものとする。
２つ目の実例がよい汎用性の高い解を必要とするときのみ、一般化を行える。

つまり、三角測量を行うためには２つ目の実例が必要だ、
「$5!=$6」はどうか？

三角測量は少し奇妙なやり方。
どうやってリファクタリングしたらよいかわからなくなったときにしか使わない。
プロダクトコードとテストコードの間の重複を一般化して取り除く方法が見ているならば、
その通り行うだけだ。

正しいコードが書けるときに、それでもテストをもう1つ追加しなければ
コードを書いてはならないというのは、少しおかしい。

*/