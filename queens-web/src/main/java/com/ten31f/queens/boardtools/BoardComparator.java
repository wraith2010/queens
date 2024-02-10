package com.ten31f.queens.boardtools;

import java.util.Comparator;

public class BoardComparator implements Comparator<Integer[]> {

	@Override
	public int compare(Integer[] board1, Integer[] board2) {

		for (int index = 0; index < board1.length; index++) {

			if (!board1[index].equals(board2[index]))
				return board1[index] - board2[index];
		}

		return 0;
	}

}
