package com.ten31f.queens.action;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ten31f.queens.domain.Solution;

public class ValidatorTest {

	@Test
	public void completeTest() {

		assertFalse(Validator.isComplete(new Solution(new int[] { 4, 6, 0, 3, 1, 7, 5, -1 })));

		assertTrue(Validator.isComplete(new Solution(new int[] { 4, 6, 0, 3, 1, 7, 5, 2 })));
	}

	@Test
	public void validateTest() {

		Solution solution = new Solution(new int[] { 4, 6, 0, 3, 1, 7, 5, 2 });

		assertTrue(Validator.validate(solution));
		assertTrue(Validator.validate(solution));

		assertFalse(Validator.validate(new Solution(new int[] { 4, 6, 2, 3, 1, 7, 5, -1 })));
		assertFalse(Validator.validate(new Solution(new int[] { 4, 6, 2, 3, 1, 7, 5, 2 })));
		assertFalse(Validator.validate(new Solution(new int[] { 4, 5, 0, 3, 1, 7, 6, 2 })));
		assertFalse(Validator.validate(new Solution(new int[] { 4, 4, 0, 3, 1, 7, 6, 2 })));

	}

}
