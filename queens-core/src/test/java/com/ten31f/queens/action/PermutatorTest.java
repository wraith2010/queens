package com.ten31f.queens.action;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ten31f.queens.domain.Solution;

public class PermutatorTest {

	static final Logger logger = Logger.getLogger(PermutatorTest.class);

	public static final int[] INITIAL_POSITTION = { 4, 6, 0, 3, 1, 7, 5, 2 };
	public static final int[] POSITION_FIRST_ORDER = { 2, 4, 7, 3, 0, 6, 1, 5 };
	public static final int[] POSITION_01 = { 2, 6, 1, 7, 4, 0, 3, 5 };
	public static final int[] POSITION_02 = { 3, 1, 7, 4, 6, 0, 2, 5 };
	public static final int[] POSITION_03 = { 5, 1, 6, 0, 3, 7, 4, 2 };
	public static final int[] POSITION_04 = { 5, 2, 0, 6, 4, 7, 1, 3 };
	public static final int[] POSITION_05 = { 5, 3, 0, 4, 7, 1, 6, 2 };

	@BeforeClass
	public static void setup() {
		BasicConfigurator.configure();
	}

	@Test
	public void test() {
		Solution solution = new Solution(INITIAL_POSITTION);

		Permutator.permutate(solution);

		List<int[]> expectedPositions = new ArrayList<>();
		expectedPositions.add(INITIAL_POSITTION);
		expectedPositions.add(POSITION_FIRST_ORDER);
		expectedPositions.add(POSITION_01);
		expectedPositions.add(POSITION_02);
		expectedPositions.add(POSITION_03);
		expectedPositions.add(POSITION_04);
		expectedPositions.add(POSITION_05);

		assertTrue(contains(INITIAL_POSITTION, solution.getPermutations()));
		assertTrue(contains(POSITION_FIRST_ORDER, solution.getPermutations()));
		assertTrue(contains(POSITION_01, solution.getPermutations()));
		assertTrue(contains(POSITION_02, solution.getPermutations()));
		assertTrue(contains(POSITION_03, solution.getPermutations()));
		assertTrue(contains(POSITION_04, solution.getPermutations()));
		assertTrue(contains(POSITION_05, solution.getPermutations()));
	}

	private boolean contains(int[] position, List<int[]> positions) {

		for (int[] possibleMatch : positions) {
			if (Arrays.equals(position, possibleMatch)) {
				return true;
			}
		}

		return false;
	}

}
