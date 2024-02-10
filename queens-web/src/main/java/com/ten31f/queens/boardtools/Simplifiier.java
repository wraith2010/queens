package com.ten31f.queens.boardtools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Simplifiier {

	private Simplifiier() {

	}

	public static Integer[] findLowestOrderBoard(Integer[] board) {

		BoardList boards = new BoardList();

		boards.add(board);

		addMirror(boards, findhorizontalMirror(board));
		addMirror(boards, findverticalMirror(board));
		addMirror(boards, findverticalMirror(findhorizontalMirror(board)));
		addMirror(boards, findNinteyDegreeMirror(board));
		addMirror(boards, findhorizontalMirror(findNinteyDegreeMirror(board)));
		addMirror(boards, findverticalMirror(findNinteyDegreeMirror(board)));
		addMirror(boards, findverticalMirror(findhorizontalMirror(findNinteyDegreeMirror(board))));

		Collections.sort(boards, new BoardComparator());

		if (log.isDebugEnabled()) {
			StringBuilder stringBuilder = new StringBuilder();
			boards.stream().forEach(b -> stringBuilder.append(Arrays.asList(b)));
			log.debug(stringBuilder.toString());
		}

		return boards.get(0);
	}

	private static void addMirror(List<Integer[]> boards, Integer[] permutation) {

		if (boards.contains(permutation)) {
			if (log.isDebugEnabled()) {
				log.debug("permutation duplicate");
			}
			return;
		}

		boards.add(permutation);

	}

	private static Integer[] findhorizontalMirror(Integer[] positions) {

		Integer[] permutation = new Integer[positions.length];

		Arrays.fill(permutation, 0, positions.length, -1);

		for (int index = 0; index < positions.length; index++) {
			permutation[index] = ((positions.length - 1) - positions[index]);
		}

		return permutation;
	}

	private static Integer[] findverticalMirror(Integer[] positions) {

		Integer[] permutation = new Integer[positions.length];

		Arrays.fill(permutation, 0, positions.length, -1);

		for (int index = 0; index < positions.length; index++) {
			permutation[((positions.length - 1) - index)] = positions[index];
		}

		return permutation;
	}

	private static Integer[] findNinteyDegreeMirror(Integer[] positions) {

		Integer[] permutation = new Integer[positions.length];

		Arrays.fill(permutation, 0, positions.length, -1);

		for (int index = 0; index < positions.length; index++) {
			permutation[positions[index]] = index;
		}

		return permutation;
	}

	@SuppressWarnings("serial")
	public static class BoardList extends ArrayList<Integer[]> {

		@Override
		public boolean contains(Object o) {

			if (o instanceof Integer[]) {
				Integer[] newBoard = (Integer[]) o;
				for (Integer[] board : this) {
					if (equals(board, newBoard)) {
						return true;
					}
				}
			}

			return false;
		}

		public boolean equals(Integer[] board1, Integer[] board2) {

			for (int index = 0; index < board1.length; index++) {
				if (!board1[index].equals(board2[index])) {
					return false;
				}
			}

			return true;
		}

	}

}
