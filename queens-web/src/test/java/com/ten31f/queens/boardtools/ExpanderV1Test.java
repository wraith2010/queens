package com.ten31f.queens.boardtools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpanderV1Test {

	@Test
	public void expanderNEquals4toNEquals5() {

		List<Integer[]> nEqualsFourBoards = Permutator.permute(4);

		printAllArrays(nEqualsFourBoards, "All Possible N=4 boards");

		List<Integer[]> nEqualsFourBoardsFiltered = nEqualsFourBoards.stream().filter(Validator::validate)
				.collect(Collectors.toList());

		assertEquals(2, nEqualsFourBoardsFiltered.size());

		printAllArrays(nEqualsFourBoardsFiltered, "Valid solutions(N=4)");

		List<Integer[]> nEqualsFourBoardsFilteredUnique = nEqualsFourBoardsFiltered.stream()
				.map(Simplifiier::findLowestOrderBoard).map(Arrays::asList).collect(Collectors.toSet()).stream()
				.map(list -> list.toArray(new Integer[0])).collect(Collectors.toList());

		printAllArrays(nEqualsFourBoardsFilteredUnique, "Valid unique solutions(N=4)");

		List<Integer[]> nEqualsFiveBoards = new ArrayList<>();

		nEqualsFourBoardsFilteredUnique.stream().map(ExpanderV1::expand)
				.forEach(expandedBoards -> nEqualsFiveBoards.addAll(expandedBoards));

		printAllArrays(nEqualsFiveBoards, "Expanded solutions(N=5)");

		printAllArrays(nEqualsFiveBoards.stream().filter(Validator::validate).collect(Collectors.toList()),
				"Valid solutions(N=5)");

	}

	private void printAllArrays(Collection<Integer[]> boards, String note) {

		StringBuilder stringBuilder = new StringBuilder();

		boards.stream().map(Arrays::toString).forEach(stringBuilder::append);

		log.info(String.format("(%s)%s boards: %s", note, boards.size(), stringBuilder.toString()));

	}

}
