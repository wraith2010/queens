package com.ten31f.queens.action.simple;

public class Generator {

	private Generator() {

	}

	public static int[] generate(int n) {
		int[] board = new int[n];

		for (int index = 0; index < n; index++) {
			board[index] = index;
		}

		return board;
	}

}
