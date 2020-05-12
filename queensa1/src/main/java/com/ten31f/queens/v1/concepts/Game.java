package com.ten31f.queens.v1.concepts;

import java.util.Arrays;

import com.ten31f.queens.domain.Solution;

public class Game {

	private Solution solution = null;

	public Game(int n) {
		setSolution(new Solution(n));
	}

	public Solution getSolution() {
		return solution;
	}

	private void setSolution(Solution solution) {
		this.solution = solution;
	}

	public void addPositionBlind(int x, int y) {
		getSolution().setPosition(x, y);
	}

	public Outcome addPosition(int x, int y) {

		if (getSolution().getPrimary()[y] != -1) {
			if (getSolution().getPrimary()[y] == x) {
				return Outcome.DUPLICATE;
			}

			return Outcome.ROW;
		}

		getSolution().getPrimary()[y] = x;

		return Outcome.CLEAR;

	}

	public boolean isfull() {

		int[] positions = getSolution().getPrimary();

		for (int y = 0; y < positions.length; y++) {
			if (positions[y] == -1)
				return false;
		}

		return true;
	}

	public boolean solved() {

		if (getSolution().isSolved())
			return true;

		if (!isfull())
			return false;

		int[] positions = getSolution().getPrimary();

		for (int y = 0; y < positions.length; y++) {
			for (int index = 0; index < positions.length; index++) {

				if (index == y)
					continue;

				// can attack row
				if (positions[y] == positions[index])
					return false;

				// can attack diagnoal
				if (Math.abs(positions[y] - positions[index]) == Math.abs(y - index))
					return false;

			}
		}

		getSolution().setSolved(true);

		return true;
	}

	private boolean addMirror(int[] permutation) {

		for (int[] existingPermutation : getSolution().getPermutations()) {
			if (Arrays.equals(existingPermutation, permutation)) {
				return false;
			}
		}

		getSolution().getPermutations().add(permutation);

		return true;
	}

	public void findMirrors() {

		addMirror(findhorizontalMirror(getSolution().getPrimary()));
		addMirror(findverticalMirror(getSolution().getPermutations().get(0)));
		addMirror(findverticalMirror(findhorizontalMirror(getSolution().getPermutations().get(0))));
		addMirror(findNinteyDegreeMirror(getSolution().getPermutations().get(0)));
		addMirror(findhorizontalMirror(findNinteyDegreeMirror(getSolution().getPermutations().get(0))));
		addMirror(findverticalMirror(findNinteyDegreeMirror(getSolution().getPermutations().get(0))));
		addMirror(findverticalMirror(
				findhorizontalMirror(findNinteyDegreeMirror(getSolution().getPermutations().get(0)))));

	}

	private int[] findhorizontalMirror(int[] positions) {

		int[] permutation = new int[positions.length];

		Arrays.fill(permutation, 0, positions.length, -1);

		for (int index = 0; index < positions.length; index++) {
			permutation[index] = ((positions.length - 1) - positions[index]);
		}

		return permutation;
	}

	private int[] findverticalMirror(int[] positions) {

		int[] permutation = new int[positions.length];

		Arrays.fill(permutation, 0, positions.length, -1);

		for (int index = 0; index < positions.length; index++) {
			permutation[((positions.length - 1) - index)] = positions[index];
		}

		return permutation;
	}

	private int[] findNinteyDegreeMirror(int[] positions) {

		int[] permutation = new int[positions.length];

		Arrays.fill(permutation, 0, positions.length, -1);

		for (int index = 0; index < positions.length; index++) {
			permutation[positions[index]] = index;
		}

		return permutation;

	}
}
