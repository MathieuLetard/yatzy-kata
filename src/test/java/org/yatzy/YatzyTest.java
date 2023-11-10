package org.yatzy;

import org.junit.*;
import org.yatzy.Yatzy.Result;

import java.util.LinkedList;

import static org.yatzy.Dices.Result.*;

import static org.junit.Assert.*;

public class YatzyTest {

	private LinkedList<Rule> rules;

	@Before
	public void init() {
		rules = Rule.getRules();
	}

	@Test
	public void testOnes() {
		Result r = Yatzy.getInstance().inferSimples(rules, new Dices(ONE, ONE, ONE, ONE, ONE));
		assertEquals(r.r, Rule.ONES);
	}

	@Test
	public void testYatzy() {
		Result r = Yatzy.getInstance().inferComplexes(rules, new Dices(ONE, ONE, ONE, ONE, ONE));
		assertEquals(r.r, Rule.YATZY);
	}

	@Test
	public void testInfer() {
		Result r = Yatzy.getInstance().infer(rules, new Dices(ONE, ONE, ONE, ONE, ONE));
		assertEquals(r.r, Rule.YATZY);
		r = Yatzy.getInstance().infer(rules, new Dices(ONE, ONE, ONE, ONE, ONE));
		assertEquals(r.r, Rule.ONES);
		r = Yatzy.getInstance().infer(rules, new Dices(ONE, ONE, ONE, ONE, ONE));
		assertEquals(r.r, Rule.CHANCE);
		r = Yatzy.getInstance().infer(rules, new Dices(ONE, ONE, ONE, ONE, ONE));
		assertEquals(r.r, Rule.FOUR_OF_A_KIND);
		r = Yatzy.getInstance().infer(rules, new Dices(ONE, ONE, ONE, ONE, ONE));
		assertEquals(r.r, Rule.THREE_OF_A_KIND);
		r = Yatzy.getInstance().infer(rules, new Dices(ONE, ONE, ONE, ONE, ONE));
		assertEquals(r.r, Rule.TWO_PAIRS);
		r = Yatzy.getInstance().infer(rules, new Dices(ONE, ONE, ONE, ONE, ONE));
		assertEquals(r.r, Rule.ONE_PAIR);
		r = Yatzy.getInstance().infer(rules, new Dices(ONE, ONE, ONE, ONE, ONE));
		assertEquals(r.r, Rule.NONE);
	}

}
