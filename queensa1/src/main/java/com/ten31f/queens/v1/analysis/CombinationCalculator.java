package com.ten31f.queens.v1.analysis;

public class CombinationCalculator {

	private CombinationCalculator() {
		// hiding constructor on static only objects
	}

	public static double calculator(double n) {

		return factorial(Math.pow(n, 2)) / (factorial(n) * (factorial(Math.pow(n, 2) - n)));

	}

	private static double factorial(double x) {

		if (x == 1)
			return 1;

		return x * factorial(x - 1);
	}

}
