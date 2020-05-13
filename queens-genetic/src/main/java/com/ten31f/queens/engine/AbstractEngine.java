package com.ten31f.queens.engine;

import java.util.Random;

public abstract class AbstractEngine {

	private long n = 0;

	private Random random;

	public AbstractEngine(long n, Random random) {
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
