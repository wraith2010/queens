package com.ten31f.queens.boardtools;

public class Validator {

	public static boolean validate(Integer[] elements) {

		for (int indexy = 0; indexy < elements.length; indexy++) {
			for (int indexx = indexy + 1; indexx < elements.length; indexx++) {

				// can attack diagnoal
				if (Math.abs(elements[indexy] - elements[indexx]) == Math.abs(indexy - indexx))
					return false;
			}
		}

		return true;
	}

}
