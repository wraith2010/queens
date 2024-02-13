package com.ten31f.queens.boardtools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.ten31f.queens.values.KNOWNSOLUTIONS;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpanderV2Test {

	@Test
	public void expanderNEquals7Test() {

		List<Integer[]> nEquals5 = new ArrayList<>() {
			{
				add(new Integer[] { 1, 4, 2, 0, 3 });
				add(new Integer[] { 0, 2, 4, 1, 3 });
			}
		};

		List<Integer[]> nEquals6 = new ArrayList<>() {
			{
				add(new Integer[] { 1, 3, 5, 0, 2, 4 });
			}
		};

		List<Integer[]> results = ExpanderV2.expand(nEquals6, nEquals5).stream().filter(Validator::validate)
				.collect(Collectors.toList());

		List<Object[]> expandedFiltered = results.stream().map(Simplifiier::findLowestOrderBoard).map(Arrays::asList)
				.collect(Collectors.toSet()).stream().map(List::toArray).collect(Collectors.toList());

		log.info(Printer.printObject(expandedFiltered));

		assertEquals(KNOWNSOLUTIONS.FUNDAMENTAL[6], expandedFiltered.size());

	}

}
