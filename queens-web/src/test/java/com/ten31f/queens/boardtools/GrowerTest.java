package com.ten31f.queens.boardtools;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.ten31f.queens.values.KNOWNSOLUTIONS;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrowerTest {

	@Test
	public void growerTest() {

		Integer[] board = { 1, 3, 0, 2 };

		List<Integer[]> currentTier = new ArrayList<>();
		currentTier.add(board);

		for (int index = 4; true; index++) {

			printAllArrays(currentTier, String.format("n = %s", index));

			assertEquals(KNOWNSOLUTIONS.FUNDAMENTAL[index - 1], currentTier.size());

			currentTier = Grower.grow(currentTier);
		}

	}

	private void printAllArrays(Collection<Integer[]> boards, String note) {

		StringBuilder stringBuilder = new StringBuilder();

		boards.stream().map(Arrays::toString).forEach(stringBuilder::append);

		log.info(String.format("(%s)%s boards: %s", note, boards.size(), stringBuilder.toString()));

	}

}
