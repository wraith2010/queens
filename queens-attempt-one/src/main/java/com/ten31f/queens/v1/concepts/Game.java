package com.ten31f.queens.v1.concepts;

import com.ten31f.queens.domain.Solution;
import com.ten31f.queens.enums.Outcome;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

	private Solution solution = null;

	public Game(int n) {
		setSolution(new Solution(n));
	}

	public void addPositionBlind(int x, int y) {
		getSolution().setPosition(x, y);
	}

	public Outcome addPosition(int x, int y) {

		if (getSolution().getPrimary()[y] != -1) {
			if (getSolution().getPrimary()[y] == x) {
				return Outcome.DUPLICATE;
			}

			return Outcome.ROW;
		}

		getSolution().getPrimary()[y] = x;

		return Outcome.CLEAR;

	}
}
