package com.ten31f.queens.boardtools;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Printer {

	public static String print(List<Integer[]> boards) {

		StringBuilder stringBuilder = new StringBuilder();

		boards.stream().forEach(board -> stringBuilder.append(String.format("%s%n", Arrays.toString(board))));

		return stringBuilder.toString();
	}

	public static String printObject(List<Object[]> boards) {

		StringBuilder stringBuilder = new StringBuilder();

		boards.stream().forEach(board -> stringBuilder.append(String.format("%s%n", Arrays.toString(board))));

		return stringBuilder.toString();
	}

	public static String print(List<Integer[]> boards, Integer[] filter) {

		return print(boards.parallelStream().filter(board -> match(board, filter)).collect(Collectors.toList()));
	}

	private static boolean match(Integer[] board, Integer[] filter) {

		for (int index = 0; index < filter.length; index++) {

			if (board[index] != filter[index]) {
				return false;
			}

		}

		return true;
	}

}
