package com.ten31f.queens.v1.domain;

import java.util.List;

import com.ten31f.queens.v1.concepts.Position;

public class GameData {

	private long n;

	private List<List<Position>> permutations;

	private Boolean solved;

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

	public List<List<Position>> getPermutations() {
		return permutations;
	}

	public void setPermutations(List<List<Position>> permutations) {
		this.permutations = permutations;
	}

	public Boolean getSolved() {
		return solved;
	}

	public void setSolved(Boolean solved) {
		this.solved = solved;
	}
}
