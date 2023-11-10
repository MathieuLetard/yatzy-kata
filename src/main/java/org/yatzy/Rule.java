package org.yatzy;

import java.util.Arrays;
import java.util.LinkedList;

import org.yatzy.Dices.Result;

public enum Rule {

	NONE {
		@Override
		public int compute(Dices ds) {
			return 0;
		}
	},

	ONES(Type.SIMPLE, Result.ONE) {
		@Override
		public int compute(Dices ds) {
			return ds.computeSum(Dices.Result.ONE);
		}
	},

	TWOS(Type.SIMPLE, Result.TWO) {
		@Override
		public int compute(Dices ds) {
			return ds.computeSum(Dices.Result.TWO);
		}
	},

	THREES(Type.SIMPLE, Result.THREE) {
		@Override
		public int compute(Dices ds) {
			return ds.computeSum(Dices.Result.THREE);
		}
	},

	FOURS(Type.SIMPLE, Result.FOUR) {
		@Override
		public int compute(Dices ds) {
			return ds.computeSum(Dices.Result.FOUR);
		}
	},

	FIVES(Type.SIMPLE, Result.FIVE) {
		@Override
		public int compute(Dices ds) {
			return ds.computeSum(Dices.Result.FIVE);
		}
	},

	SIXES(Type.SIMPLE, Result.SIX) {
		@Override
		public int compute(Dices ds) {
			return ds.computeSum(Dices.Result.SIX);
		}
	},

	CHANCE(Type.COMPLEX) {
		@Override
		public int compute(Dices ds) {
			return ds.getRs().stream().map((x) -> x.v).mapToInt(Integer::intValue).sum();
		}
	},

	YATZY(Type.COMPLEX) {
		@Override
		public int compute(Dices ds) {
			int c = ds.computeMaxEquals();
			if (c == Dices.NB_DICES)
				return 50;
			return 0;
		}
	},

	ONE_PAIR(Type.COMPLEX) {
		@Override
		public int compute(Dices ds) {
			return ds.computeSumPair(1);
		}
	},

	TWO_PAIRS(Type.COMPLEX) {
		@Override
		public int compute(Dices ds) {
			return ds.computeSumPair(2);
		}
	},

	FOUR_OF_A_KIND(Type.COMPLEX) {
		@Override
		public int compute(Dices ds) {
			return ds.computeOfAKind(4);
		}
	},

	THREE_OF_A_KIND(Type.COMPLEX) {
		@Override
		public int compute(Dices ds) {
			return ds.computeOfAKind(3);
		}
	},

	SMALL_STRAIGHT(Type.COMPLEX) {
		@Override
		public int compute(Dices ds) {
			if (ds.isStraight(Result.ONE, Result.TWO, Result.THREE, Result.FOUR, Result.FIVE))
				return 15;
			return 0;
		}
	},

	LARGE_STRAIGHT(Type.COMPLEX) {
		@Override
		public int compute(Dices ds) {
			if (ds.isStraight(Result.TWO, Result.THREE, Result.FOUR, Result.FIVE, Result.SIX))
				return 20;
			return 0;
		}
	},

	FULL_HOUSE(Type.COMPLEX) {
		@Override
		public int compute(Dices ds) {
			int[] counts = ds.computeCounts();
			boolean twoOfAKind = false;
			int i;
			int pairIndex = 0;
			boolean threeOfAKind = false;
			int tripleIndex = 0;
			for (i = 0; i != Dices.LENGTH; i += 1)
				if (counts[i] == 2) {
					twoOfAKind = true;
					pairIndex = i + 1;
				}

			for (i = 0; i != Dices.LENGTH; i += 1)
				if (counts[i] == 3) {
					threeOfAKind = true;
					tripleIndex = i + 1;
				}

			if (twoOfAKind && threeOfAKind)
				return pairIndex * 2 + tripleIndex * 3;
			else
				return 0;
		}
	};

	public static final LinkedList<Rule> getRules() {
		return new LinkedList<>(Arrays.asList(ONES, TWOS, THREES, FOURS, FIVES, SIXES, CHANCE, YATZY, ONE_PAIR,
				TWO_PAIRS, FOUR_OF_A_KIND, THREE_OF_A_KIND, SMALL_STRAIGHT, LARGE_STRAIGHT, FULL_HOUSE));
	}

	public static enum Type {
		SIMPLE, COMPLEX
	}

	public final Type t;
	public final Result r;

	private Rule() {
		this.t = null;
		this.r = null;
	}

	private Rule(Type t) {
		this.t = t;
		this.r = null;
	}

	private Rule(Type t, Result r) {
		this.t = t;
		this.r = r;
	}

	public abstract int compute(Dices dice);

}
