package com.ten31f.queens.boardtools;

import java.util.Arrays;
import java.util.Collection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardLogger {

	public static void printAllArrays(Collection<Integer[]> boards, String note) {

		StringBuilder stringBuilder = new StringBuilder();

		boards.stream().map(Arrays::toString).forEach(stringBuilder::append);

		log.info(String.format("(%s)%s boards: %s", note, boards.size(), stringBuilder.toString()));

	}
}
