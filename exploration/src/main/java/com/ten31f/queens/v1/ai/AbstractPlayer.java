package com.ten31f.queens.v1.ai;

import java.util.Random;

public abstract class AbstractPlayer {

	private long n = 0;

	private Random random;

	public AbstractPlayer(long n, Random random) {
		setN(n);
		setRandom(random);
	}

	protected long getN() {
		return n;
	}

	protected void setN(long n) {
		this.n = n;
	}

	protected Random getRandom() {
		return random;
	}

	protected void setRandom(Random random) {
		this.random = random;
	}

}
