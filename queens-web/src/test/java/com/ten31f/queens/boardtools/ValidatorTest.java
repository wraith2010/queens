package com.ten31f.queens.boardtools;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class ValidatorTest {

	@Test
	public void validationTestNEqualsFour() {

		Integer[] board = { 2, 0, 3, 1 };

		assertTrue(Validator.validate(board));

	}

	@Test
	public void validationTestNEqualsFourDiagonal() {

		Integer[] board = { 0, 1, 2, 3 };

		assertFalse(Validator.validate(board));

	}

	@Test
	public void validationTestNEqualsFourFilter() {

		List<Integer[]> boards = Permutator.permute(4);

		assertEquals(24, boards.size());

		List<Integer[]> boardsFiltered = boards.stream().filter(Validator::validate).collect(Collectors.toList());

		assertEquals(2, boardsFiltered.size());

	}

}
