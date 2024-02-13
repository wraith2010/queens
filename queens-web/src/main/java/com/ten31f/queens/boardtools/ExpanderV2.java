package com.ten31f.queens.boardtools;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpanderV2 {

	public static List<Integer[]> expand(List<Integer[]> nMinus1, List<Integer[]> nMinus2) {

		int nForNMinus1 = nMinus1.get(0).length;
		int nForNMinus2 = nMinus2.get(0).length;

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format("%nn = %s%n", nForNMinus1));
		stringBuilder.append(Printer.print(nMinus1));

		stringBuilder.append(String.format("n = %s%n", nForNMinus2));
		stringBuilder.append(Printer.print(nMinus2));

		List<Integer[]> rotatedBoards = nMinus2.parallelStream().map(ExpanderV2::rotateBoard)
				.flatMap(List::parallelStream).collect(Collectors.toList());

		stringBuilder.append(String.format("Rotated(%s->%s)%n", nMinus2.size(), rotatedBoards.size()));
		stringBuilder.append(Printer.print(rotatedBoards, new Integer[] { 1, 4 }));

		List<Integer[]> expandedBoards = rotatedBoards.parallelStream().map(ExpanderV2::expandByOne)
				.flatMap(List::parallelStream).collect(Collectors.toList());

		stringBuilder.append(String.format("Expanded(%s->%s)%n", rotatedBoards.size(), expandedBoards.size()));
		stringBuilder.append(Printer.print(expandedBoards, new Integer[] { 1, 4 }));

		expandedBoards.addAll(nMinus1);

		List<Integer[]> rotatedBoards2 = expandedBoards.parallelStream().map(ExpanderV2::rotateBoard)
				.flatMap(List::parallelStream).collect(Collectors.toList());

		stringBuilder.append(String.format("Rotated(%s->%s)%n", expandedBoards.size(), rotatedBoards2.size()));
		stringBuilder.append(Printer.print(rotatedBoards2, new Integer[] { 1, 4, 3, 0, 2, 5 }));

		List<Integer[]> boards = rotatedBoards2.parallelStream().map(ExpanderV2::expandByOne)
				.flatMap(List::parallelStream).collect(Collectors.toList());

		stringBuilder.append(String.format("Eaxpanded(%s->%s):%n", rotatedBoards2.size(), boards.size()));
		stringBuilder.append(Printer.print(boards, new Integer[] { 1, 4, 6, 3, 0, 2, 5 }));

		log.info(stringBuilder.toString());

		return boards;
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

	private static List<Integer[]> rotateBoard(Integer[] source) {

		List<Integer[]> results = new ArrayList<>();

		results.add(source);

		for (int index = 1; index < source.length; index++) {
			Integer[] board = new Integer[source.length];
			for (int subIndex = 0; subIndex < source.length; subIndex++) {
				board[subIndex] = source[(subIndex + index) % source.length];
			}
			results.add(board);
		}

		return results;
	}

}
