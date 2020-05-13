package com.ten31f.queens.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class RollingSolution extends AbstractEngine implements Engine {

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

		int x = getRandom().nextInt(getFiles().size());

		List<Entry<Integer, Integer>> filespositions = new ArrayList<>(getFiles().entrySet());

		return new int[] {filespositions.get(x).getValue(), getRow()};
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

	protected void setGuesses(long guesses) {
		this.guesses = guesses;
	}

	protected long getGuesses() {
		return guesses;
	}

	protected Map<Integer, Integer> getFiles() {
		return files;
	}

	protected void setFiles(Map<Integer, Integer> files) {
		this.files = files;
	}

	protected void setRow(int row) {
		this.row = row;
	}

	protected int getRow() {
		return row;
	}

	protected void advanceRow() {
		setRow(getRow() + 1);
	}
}
