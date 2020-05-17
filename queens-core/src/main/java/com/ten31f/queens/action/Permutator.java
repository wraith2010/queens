package com.ten31f.queens.action;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.ten31f.queens.domain.Solution;

public class Permutator {

	public static void permutate(Solution solution) {

		addMirror(solution, findhorizontalMirror(solution.getPrimary()));
		addMirror(solution, findverticalMirror(solution.getPrimary()));
		addMirror(solution, findverticalMirror(findhorizontalMirror(solution.getPrimary())));
		addMirror(solution, findNinteyDegreeMirror(solution.getPrimary()));
		addMirror(solution, findverticalMirror(findNinteyDegreeMirror(solution.getPrimary())));
		addMirror(solution, findverticalMirror(findhorizontalMirror(findNinteyDegreeMirror(solution.getPrimary()))));

		Collections.sort(solution.getPermutations(), new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {

				for (int index = 0; index < o1.length; index++) {
					if (o1[index] < o2[index])
						return -1;

					if (o1[index] > o2[index])
						return 1;
				}

				return 0;
			}
		});
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
