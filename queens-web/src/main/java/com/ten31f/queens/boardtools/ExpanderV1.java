package com.ten31f.queens.boardtools;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpanderV1 {

	public static List<Integer[]> expand(Integer[] board) {

		return rotateBoard(board).parallelStream().map(ExpanderV1::expandByOne).flatMap(List::parallelStream)
				.collect(Collectors.toList());

	}

	public static List<Integer[]> expandByOne(Integer[] source) {

		List<Integer[]> results = new ArrayList<>();

		for (int index = 0; index < source.length + 1; index++) {
			Integer[] board = new Integer[source.length + 1];
			board[index] = source.length;

			for (int subIndex = 0; subIndex <= source.length; subIndex++) {
				if (subIndex < index) {
					board[subIndex] = source[subIndex];
				} else if (subIndex > index) {
					board[subIndex] = source[subIndex - 1];
				}
			}

			results.add(board);
		}

		return results;

	}

	public static List<Integer[]> rotateBoard(Integer[] source) {

		List<Integer[]> results = new ArrayList<>();

		results.add(source);

		for (int index = 1; index < source.length + 1; index++) {

			Integer[] board = new Integer[source.length];

			for (int subIndex = 0; subIndex < source.length; subIndex++) {
				board[subIndex] = source[(subIndex + index) % source.length];
			}

			results.add(board);

		}

		return results;

	}

}
