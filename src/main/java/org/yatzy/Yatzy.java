package org.yatzy;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Yatzy {
	
	private static final Yatzy instance = new Yatzy();
	
	public static Yatzy getInstance() {
		return instance;
	}
	
	public static class Result {
		public final Rule r;
		public final int score;
		public Result(Rule r, int score) {
			this.r = r;
			this.score = score;
		}
	}
	
	public Result inferSimples(LinkedList<Rule> rules, Dices ds) {
		List<Rule> simples = rules.stream().filter((x) -> x.t == Rule.Type.SIMPLE).collect(Collectors.toList());
		for (Rule r : simples) {
			if (ds.computeCount(r.r) >= 3) {
				return new Result(r, r.compute(ds));
			}
		}
		return new Result(Rule.NONE, 0);
	}
	
	public Result inferComplexes(LinkedList<Rule> rules, Dices ds) {
		List<Rule> complexes = rules.stream().filter((x) -> x.t == Rule.Type.COMPLEX).collect(Collectors.toList());
		int score = 0;
		Rule res = null;
		for (Rule r : complexes) {
			int s = r.compute(ds);
			if (s > score) {
				score = s;
				res = r;
			}
		}
		if (res != null) {
			return new Result(res, score);
		}
		return new Result(Rule.NONE, 0);
	}

	public Result infer (LinkedList<Rule> rules, Dices ds) {

		Result rc = inferComplexes(rules, ds);
		
		Result rs = inferSimples(rules, ds);
		
		if (rs.score >= rc.score) {
			rules.remove(rs.r);
			return rs;
		}
		if (rc.r != Rule.NONE) {
			rules.remove(rc.r);
		}
		return rc;
	}
}
