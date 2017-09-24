package com.ten31f.queens.v1.domain;

import java.util.ArrayList;
import java.util.List;

import com.ten31f.queens.v1.concepts.Position;

public class PositionHeat extends Position {

	private List<Integer> heatOrdinals = new ArrayList<>();
	private List<Integer> ordinalTotals = null;

	private int N;

	public PositionHeat(int x, int y, int n, List<Integer> ordinalTotals) {
		super(x, y);
		setN(n);
		setHeatOrdinals(new ArrayList<>());
		setOrdinalTotals(ordinalTotals);
		init();
	}

	private void init() {
		for (int index = 0; index < getN(); index++) {
			getHeatOrdinals().add(0);
		}
	}

	public void recordeHeat(int ordinal) {
		getHeatOrdinals().set(ordinal, getHeatOrdinals().get(ordinal) + 1);
	}

	public List<Integer> getHeatOrdinals() {
		return heatOrdinals;
	}

	public void setHeatOrdinals(List<Integer> heatOrdinals) {
		this.heatOrdinals = heatOrdinals;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int temprature() {

		int negative = 0;

		for (int index = 1; index < getN(); index++) {
			negative += (int) (getHeatOrdinals().get(index) + Math.pow(-10, index));
		}

		return negative;
	}

	@Override
	public String toString() {

		StringBuilder stringBuilder = new StringBuilder(super.toString());

		for (int index = 0; index < getN(); index++) {
			stringBuilder.append('[');
			stringBuilder.append(getHeatOrdinals().get(index) - getOrdinalTotals().get(index));
			stringBuilder.append(']');
		}

		return stringBuilder.toString();
		// return super.toString() + '[' + temprature() + ']';
	}

	public List<Integer> getOrdinalTotals() {
		return ordinalTotals;
	}

	public void setOrdinalTotals(List<Integer> ordinalTotals) {
		this.ordinalTotals = ordinalTotals;
	}

}
