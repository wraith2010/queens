package com.ten31f.queens.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

	public Solution(int[] positions) {
		setN(positions.length);
		setPermutations(new ArrayList<int[]>());
		getPermutations().add(positions);
	}

	public void setPosition(int x, int y) {
		if (getInitialPosition() == -1)
			setInitialPosition(y);

		getPrimary()[y] = x;
	}

	public int[] getPrimary() {
		if (getPermutations() != null)
			return getPermutations().get(0);

		return null;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(System.lineSeparator());

		for (int[] permutation : getPermutations()) {
			stringBuilder.append(Arrays.toString(permutation));
			stringBuilder.append(System.lineSeparator());
		}

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
