package com.ten31f.queens.v1.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ten31f.queens.enums.Outcome;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RollingSolution extends AbstractPlayer implements Player {

	private long guesses = 0;

	private Map<Integer, Integer> files = null;

	private int row = 0;

	public RollingSolution(long n, Random random) {
		super(n, random);
		reset();
	}

	@Override
	public void reset() {

		setGuesses(getN());
		setFiles(new HashMap<>());
		for (int x = 0; x < getN(); x++) {
			getFiles().put(x, x);
		}
		setRow(0);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ten31f.ai.Player#nextPosition()
	 */
	@Override
	public int[] nextPosition() {

		int x = getRandom().nextInt((getFiles().size() > 0) ? getFiles().size() : 1);

		List<Entry<Integer, Integer>> filespositions = new ArrayList<>(getFiles().entrySet());

		return new int[] { filespositions.get(x).getValue(), getRow() };
	}

	@Override
	public void digest(int[] position, Outcome outcome) {

		switch (outcome) {
		case CLEAR:

			advanceRow();
			setGuesses(getRow());
			getFiles().remove(position[0]);

			break;

		case FILE:
		case ROW:
		case DIAGONAL:
		case DUPLICATE:

			setGuesses(getGuesses() - 1);
			break;

		}

	}

	@Override
	public String getName() {

		return "RollingSolution";
	}

	@Override
	public boolean giveUp() {
		return getGuesses() <= 0;
	}

	protected void advanceRow() {
		setRow(getRow() + 1);
	}
}
