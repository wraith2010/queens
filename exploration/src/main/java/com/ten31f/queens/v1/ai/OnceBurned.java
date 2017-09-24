package com.ten31f.queens.v1.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.ten31f.queens.v1.concepts.Outcome;
import com.ten31f.queens.v1.concepts.Position;

/**
 * Once Burned Twice Shy
 * 
 * @author bmitchell
 *
 */
public class OnceBurned extends AbstractPlayer implements Player {

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
	public Position nextPosition() {

		int x = getRandom().nextInt(getxValues().size());
		int y = getRandom().nextInt(getyValues().size());

		List<Entry<Integer, Integer>> xPositions = new ArrayList<>(getxValues().entrySet());
		List<Entry<Integer, Integer>> yPositions = new ArrayList<>(getyValues().entrySet());

		return new Position(xPositions.get(x).getValue(), yPositions.get(y).getValue());
	}

	@Override
	public void digest(Position position, Outcome outcome) {

		switch (outcome) {
		case CLEAR:

			getxValues().remove(position.getX());
			getyValues().remove(position.getY());
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
