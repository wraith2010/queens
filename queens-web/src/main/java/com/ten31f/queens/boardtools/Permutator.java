package com.ten31f.queens.boardtools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Permutator {

	public static List<Integer[]> permute(int size) {

		Integer[] elements = new Integer[size];
		for (int index = 0; index < size; index++) {
			elements[index] = index;
		}

		List<Integer[]> results = permute(size, elements, new ArrayList<>());
		log.info(String.format("(n = %s ==> %s)", size, results.size()));
		return results;

	}

	private static List<Integer[]> permute(int n, Integer[] elements, List<Integer[]> results) {

		if (n == 1) {
			results.add(Arrays.copyOf(elements, elements.length));
		} else {
			for (int i = 0; i < n - 1; i++) {
				permute(n - 1, elements, results);
				if (n % 2 == 0) {
					swap(elements, i, n - 1);
				} else {
					swap(elements, 0, n - 1);
				}
			}
			permute(n - 1, elements, results);
		}

		return results;
	}

	private static void swap(Integer[] elements, int a, int b) {
		int tmp = elements[a];
		elements[a] = elements[b];
		elements[b] = tmp;
	}

}
