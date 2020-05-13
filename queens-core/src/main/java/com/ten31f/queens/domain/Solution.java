package com.ten31f.queens.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	private static final String SQUARE = "[%s]";

	private long n = 0;
	private List<int[]> permutations;
	private int initialPosition = 0;

	private boolean solved = false;

	public Solution(long n) {
		setN(n);
		setPermutations(new ArrayList<int[]>());
		getPermutations().add(new int[(int) n]);
		Arrays.fill(getPrimary(), 0, (int) n, -1);
	}

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

	private void setInitialPosition(int initialPosition) {
		this.initialPosition = initialPosition;
	}

	public int getInitialPosition() {
		return initialPosition;
	}

	public void setPosition(int x, int y) {
		if (getInitialPosition() == -1)
			setInitialPosition(y);

		getPrimary()[y] = x;
	}
	
	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public List<int[]> getPermutations() {
		return permutations;
	}

	private void setPermutations(List<int[]> permutations) {
		this.permutations = permutations;
	}

	public int[] getPrimary() {
		if (getPermutations() != null)
			return getPermutations().get(0);

		return null;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(Arrays.toString(getPrimary()));
		stringBuilder.append(System.lineSeparator());

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				stringBuilder.append(String.format(SQUARE, (getPrimary()[y] == x) ? 'Q' : ' '));
			}
			stringBuilder.append("\n");
		}

		return stringBuilder.toString();
	}
}
