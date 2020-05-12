package com.ten31f.queens;

import org.junit.Test;

import com.ten31f.queens.domain.Solution;

public class UniqueStartingPositions {

	private static final int LIMIT_N = 19;

	@Test
	public void uniqueStartingPositionsTest() {

		for (int n = 4; n < LIMIT_N; n++) {

			int half = n / 2 + n % 2;

			for (int y = 0; y < half; y++) {
				for (int x = 0; x < half; x++) {

					Solution solution = new Solution(n);
					solution.setPosition(x, y);

					System.out.println(solution);

				}
			}

		}

	}

}
