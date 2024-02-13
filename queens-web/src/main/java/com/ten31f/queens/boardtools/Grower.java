package com.ten31f.queens.boardtools;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Grower {

	public static List<Integer[]> grow(List<Integer[]> previousTier) {

		List<Integer[]> expanded = previousTier.stream().map(ExpanderV1::expand).flatMap(List::stream)
				.filter(Validator::validate).collect(Collectors.toList());

//		List<Integer[]> expanded = previousTier.stream().map(Expander::expand).flatMap(List::stream)
//				.collect(Collectors.toList());
		
		
		BoardLogger.printAllArrays(expanded, "expanded Set");

		List<Integer[]> expandedFiltered = expanded.stream().map(Simplifiier::findLowestOrderBoard).map(Arrays::asList)
				.collect(Collectors.toSet()).stream().map(list -> list.toArray(new Integer[0]))
				.collect(Collectors.toList());

		BoardLogger.printAllArrays(expandedFiltered, "filter Set");

		log.info(String.format("previous tier(%s) -> expanded(%s) -> result(%s)", previousTier.size(), expanded.size(),
				expandedFiltered.size()));

		return expandedFiltered;

	}

}
