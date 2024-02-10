package com.ten31f.queens.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Actions {

	private Actions() {

	}

	private static void swap(Integer[] elements, int a, int b) {
		int tmp = elements[a];
		elements[a] = elements[b];
		elements[b] = tmp;
	}

	public static Set<Integer[]> expand(Set<Integer[]> sources) {

		Set<Integer[]> results = new HashSet<>();

		sources.stream().forEach(source -> results.addAll(expand(source).stream().filter(Actions::validate).toList()));

		return results;
	}

	private static List<Integer[]> expand(Integer[] source) {

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

	public static boolean validate(Integer[] elements) {

		for (int y = 0; y < elements.length; y++) {
			for (int index = 0; index < elements.length; index++) {

				if (index == y)
					continue;

				// can attack row
				if (elements[y] == elements[index])
					return false;

				// can attack diagnoal
				if (Math.abs(elements[y] - elements[index]) == Math.abs(y - index))
					return false;

			}
		}

		return true;
	}

}
