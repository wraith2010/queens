package com.ten31f.queens.engine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * Once Burned Twice Shy
 * 
 * @author bmitchell
 *
 */
public class OnceBurned extends AbstractEngine implements Engine {

	static final Logger logger = Logger.getLogger(OnceBurned.class);

	private long guesses = 0;

	private Map<Integer, Integer> xValues = null;
	private Map<Integer, Integer> yValues = null;

	public OnceBurned(long n, Random random) {
		super(n, random);

		reset();
	}

	@Override
	public void reset() {

		setGuesses(getN() * getN());

		setxValues(new HashMap<>());
		setyValues(new HashMap<>());

		for (int x = 0; x < getN(); x++) {

			getxValues().put(x, x);
			getyValues().put(x, x);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ten31f.ai.Player#nextPosition()
	 */
	@Override
	public int[] nextPosition() {

		Collection<Integer> xColletion = getxValues().values();
		Collection<Integer> yColletion = getyValues().values();

		return new int[] { randomValue(xColletion), randomValue(yColletion) };
	}

	public int randomValue(Collection<Integer> colletion) {

		Iterator<Integer> iterator = colletion.iterator();

		int index = (colletion.size() > 0) ? getRandom().nextInt(colletion.size()) : 0;

		if (index != 0) {
			for (int x = 0; x < index; x++) {
				iterator.next();
			}
		}

		return iterator.next();
	}

	@Override
	public void digest(int[] position, Outcome outcome) {

		switch (outcome) {
		case CLEAR:

			getxValues().remove(position[0]);
			getyValues().remove(position[1]);
			setGuesses(getxValues().size() * getyValues().size());
			break;

		case FILE:
		case ROW:
		case DIAGONAL:
		case DUPLICATE:

			setGuesses(getGuesses() - 1);
			break;

		}

		if (giveUp())
			return;

		if (getxValues().size() == 1 && getyValues().size() == 1)
			setGuesses(1);

	}

	@Override
	public String getName() {

		return "OnceBurned";
	}

	@Override
	public boolean giveUp() {
		return getGuesses() <= 0;
	}

	private void setGuesses(long guesses) {
		this.guesses = guesses;
	}

	private long getGuesses() {
		return guesses;
	}

	private Map<Integer, Integer> getxValues() {
		return xValues;
	}

	private void setxValues(Map<Integer, Integer> xValues) {
		this.xValues = xValues;
	}

	private Map<Integer, Integer> getyValues() {
		return yValues;
	}

	private void setyValues(Map<Integer, Integer> yValues) {
		this.yValues = yValues;
	}
}
