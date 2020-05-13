package com.ten31f.queens.action;

import java.util.Arrays;

import com.ten31f.queens.domain.Solution;

public class Permutator {

	public static void permutate(Solution solution) {

		addMirror(solution, findhorizontalMirror(solution.getPrimary()));
		addMirror(solution, findverticalMirror(solution.getPrimary()));
		addMirror(solution, findverticalMirror(findhorizontalMirror(solution.getPrimary())));
		addMirror(solution, findNinteyDegreeMirror(solution.getPrimary()));
		addMirror(solution, findverticalMirror(findNinteyDegreeMirror(solution.getPrimary())));
		addMirror(solution, findverticalMirror(findhorizontalMirror(findNinteyDegreeMirror(solution.getPrimary()))));
	}

	public static boolean addMirror(Solution solution, int[] permutation) {

		for (int[] existingPermutation : solution.getPermutations()) {
			if (Arrays.equals(existingPermutation, permutation)) {
				return false;
			}
		}

		solution.getPermutations().add(permutation);

		return true;
	}

	public static int[] findhorizontalMirror(int[] positions) {

		int[] permutation = new int[positions.length];

		Arrays.fill(permutation, 0, positions.length, -1);

		for (int index = 0; index < positions.length; index++) {
			permutation[index] = ((positions.length - 1) - positions[index]);
		}

		return permutation;
	}

	public static int[] findverticalMirror(int[] positions) {

		int[] permutation = new int[positions.length];

		Arrays.fill(permutation, 0, positions.length, -1);

		for (int index = 0; index < positions.length; index++) {
			permutation[((positions.length - 1) - index)] = positions[index];
		}

		return permutation;
	}

	public static int[] findNinteyDegreeMirror(int[] positions) {

		int[] permutation = new int[positions.length];

		Arrays.fill(permutation, 0, positions.length, -1);

		for (int index = 0; index < positions.length; index++) {
			permutation[positions[index]] = index;
		}

		return permutation;

	}

}
