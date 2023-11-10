package org.yatzy;

import org.junit.*;

import static org.junit.Assert.*;

import static org.yatzy.Rule.*;
import static org.yatzy.Dices.Result.*;

public class RuleTest {

	@Test
	public void testChance() {
		assertEquals(15, CHANCE.compute(new Dices(TWO, THREE, FOUR, FIVE, ONE)));
		assertEquals(16, CHANCE.compute(new Dices(THREE, THREE, FOUR, FIVE, ONE)));
	}

	@Test
	public void testYatzy() {
		assertEquals(50, YATZY.compute(new Dices(FOUR, FOUR, FOUR, FOUR, FOUR)));
		assertEquals(50, YATZY.compute(new Dices(SIX, SIX, SIX, SIX, SIX)));
		assertEquals(0, YATZY.compute(new Dices(SIX, SIX, SIX, SIX, THREE)));
	}

	@Test
	public void testOnes() {
		assertEquals(1, ONES.compute(new Dices(ONE, TWO, THREE, FOUR, FIVE)));
		assertEquals(2, ONES.compute(new Dices(ONE, TWO, ONE, FOUR, FIVE)));
		assertEquals(0, ONES.compute(new Dices(SIX, TWO, TWO, FOUR, FIVE)));
		assertEquals(4, ONES.compute(new Dices(ONE, TWO, ONE, ONE, ONE)));
	}

	@Test
	public void testTwos() {
		assertEquals(4, TWOS.compute(new Dices(ONE, TWO, THREE, TWO, SIX)));
		assertEquals(10, TWOS.compute(new Dices(TWO, TWO, TWO, TWO, TWO)));
	}

	@Test
	public void testThrees() {
		assertEquals(6, THREES.compute(new Dices(ONE, TWO, THREE, TWO, THREE)));
		assertEquals(12, THREES.compute(new Dices(TWO, THREE, THREE, THREE, THREE)));
	}

	@Test
	public void testFours() {
		assertEquals(12, FOURS.compute(new Dices(FOUR, FOUR, FOUR, FIVE, FIVE)));
		assertEquals(8, FOURS.compute(new Dices(FOUR, FOUR, FIVE, FIVE, FIVE)));
		assertEquals(4, FOURS.compute(new Dices(FOUR, FIVE, FIVE, FIVE, FIVE)));
	}

	@Test
	public void testFives() {
		assertEquals(10, FIVES.compute(new Dices(FOUR, FOUR, FOUR, FIVE, FIVE)));
		assertEquals(15, FIVES.compute(new Dices(FOUR, FOUR, FIVE, FIVE, FIVE)));
		assertEquals(20, FIVES.compute(new Dices(FOUR, FIVE, FIVE, FIVE, FIVE)));
	}

	@Test
	public void testSixes() {
		assertEquals(0, SIXES.compute(new Dices(FOUR, FOUR, FOUR, FIVE, FIVE)));
		assertEquals(6, SIXES.compute(new Dices(FOUR, FOUR, SIX, FIVE, FIVE)));
		assertEquals(18, SIXES.compute(new Dices(SIX, FIVE, SIX, SIX, FIVE)));
	}

	@Test
	public void testOnePair() {
		assertEquals(3, ONE_PAIR.compute(new Dices(THREE, FOUR, THREE, FIVE, SIX)));
		assertEquals(5, ONE_PAIR.compute(new Dices(FIVE, THREE, THREE, THREE, FIVE)));
		assertEquals(6, ONE_PAIR.compute(new Dices(FIVE, THREE, SIX, SIX, FIVE)));
	}

	@Test
	public void testTwoPairs() {
		assertEquals(16, TWO_PAIRS.compute(new Dices(THREE, THREE, FIVE, FOUR, FIVE)));
		assertEquals(16, TWO_PAIRS.compute(new Dices(THREE, THREE, FIVE, FIVE, FIVE)));
	}

	@Test
	public void testThreeOfAKind() {
		assertEquals(9, THREE_OF_A_KIND.compute(new Dices(THREE, THREE, THREE, FOUR, FIVE)));
		assertEquals(15, THREE_OF_A_KIND.compute(new Dices(FIVE, THREE, FIVE, FOUR, FIVE)));
		assertEquals(9, THREE_OF_A_KIND.compute(new Dices(THREE, THREE, THREE, THREE, FIVE)));
	}

	@Test
	public void testFourOfAKind() {
		assertEquals(12, FOUR_OF_A_KIND.compute(new Dices(THREE, THREE, THREE, THREE, FIVE)));
		assertEquals(20, FOUR_OF_A_KIND.compute(new Dices(FIVE, FIVE, FIVE, FOUR, FIVE)));
		assertEquals(12, FOUR_OF_A_KIND.compute(new Dices(THREE, THREE, THREE, THREE, THREE)));
	}

	@Test
	public void testSmallStraight() {
		assertEquals(15, SMALL_STRAIGHT.compute(new Dices(ONE, TWO, THREE, FOUR, FIVE)));
		assertEquals(15, SMALL_STRAIGHT.compute(new Dices(TWO, THREE, FOUR, FIVE, ONE)));
		assertEquals(0, SMALL_STRAIGHT.compute(new Dices(ONE, TWO, TWO, FOUR, FIVE)));
	}

	@Test
	public void testLargeStraight() {
		assertEquals(20, LARGE_STRAIGHT.compute(new Dices(SIX, TWO, THREE, FOUR, FIVE)));
		assertEquals(20, LARGE_STRAIGHT.compute(new Dices(TWO, THREE, FOUR, FIVE, SIX)));
		assertEquals(0, LARGE_STRAIGHT.compute(new Dices(ONE, TWO, TWO, FOUR, FIVE)));
	}

	@Test
	public void testFullHouse() {
		assertEquals(18, FULL_HOUSE.compute(new Dices(SIX, TWO, TWO, TWO, SIX)));
		assertEquals(0, FULL_HOUSE.compute(new Dices(TWO, THREE, FOUR, FIVE, SIX)));
	}
}
