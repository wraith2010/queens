package com.ten31f.queens.action;

import com.ten31f.queens.domain.Solution;

public class Validator {

	public static boolean validate(Solution solution) {

		if (solution.isSolved())
			return true;

		if (!isComplete(solution))
			return false;

		int[] positions = solution.getPrimary();

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

		solution.setSolved(true);

		return true;

	}

	public static boolean isComplete(Solution solution) {

		int[] positions = solution.getPrimary();

		for (int y = 0; y < positions.length; y++) {
			if (positions[y] == -1)
				return false;
		}

		return true;
	}

}
