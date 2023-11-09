package org.yatzy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dices {
	
	public static final int LENGTH = 6;
	public static final int NB_DICES = 5;
	
	public static enum Result {
		ONE(1),
		TWO(2),
		THREE(3),
		FOUR(4),
		FIVE(5),
		SIX(6);
		public final int v;
		Result(int v) {
			this.v = v;
		}
	};

    private final List<Result> rs;
    private final Result r1;
    private final Result r2;
    private final Result r3;
    private final Result r4;
    private final Result r5;

    public Dices(Result r1, Result r2, Result r3, Result r4, Result r5) {
    	this.r1 = r1;
    	this.r2 = r2;
    	this.r3 = r3;
    	this.r4 = r4;
    	this.r5 = r5;
        this.rs = Collections.unmodifiableList(Arrays.asList(r1, r2, r3, r4, r5));
    }
    public List<Result> getRs() {
    	return this.rs;
    }
	public Result getR1() {
		return r1;
	}
	public Result getR2() {
		return r2;
	}
	public Result getR3() {
		return r3;
	}
	public Result getR4() {
		return r4;
	}
	public Result getR5() {
		return r5;
	}
	public int[] computeCounts() {
		int[] counts = new int[LENGTH];
		int[] ds = rs.stream().map((x) -> x.v).mapToInt(Integer::intValue).toArray();
        for (int d : ds) counts[d-1]++;
        return counts;
	}
	public int computeSum(Dices.Result r) {
		return rs.stream().map((x) -> x.v).mapToInt(Integer::intValue).filter((d) -> d == r.v)
                .sum();
	}
	public int computeSumPair(int nb) {
		int[] counts = this.computeCounts();
		int score = 0;
		int n = 0;
        for (int i = 0; i < Dices.LENGTH; i += 1) {
            if (counts[Dices.LENGTH-i-1] >= 2) {
                n++;
                score += (Dices.LENGTH-i);
                if (n >= nb) break;
            }
        }
        return score * nb;
	}
	public int computeMaxEquals() {
		int[] counts = computeCounts();
		int max = 0;
		for (int c : counts) if (c > max) max = c;
		return max;
	}
	
	public int computeCount(Result r) {
		int[] counts = computeCounts();
		return counts[r.v - 1];
	}
	
	public int computeOfAKind(int n) {
		int[] counts = computeCounts();
        for (int i = 0; i < Dices.LENGTH; i++)
            if (counts[i] >= n)
                return (i+1) * n;
        return 0;
	}
	
	public boolean isStraight(Result r1, Result r2, Result r3, Result r4, Result r5) {
		int[] counts = computeCounts();
        if (counts[r1.v - 1] == 1 &&
        	counts[r2.v - 1] == 1 &&
        	counts[r3.v - 1] == 1 &&
        	counts[r4.v - 1] == 1 &&
        	counts[r5.v - 1] == 1)
            return true;
        return false;
	}
}
