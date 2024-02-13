package com.ten31f.queens.boardtools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

public class PermutatorTest {

	@Test
	public void test() {

		List<Integer[]> boards = Permutator.permute(4);

		assertEquals(24, boards.size());

	}

}
