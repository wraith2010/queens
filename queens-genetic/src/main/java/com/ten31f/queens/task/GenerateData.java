package com.ten31f.queens.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ten31f.queens.action.Permutator;
import com.ten31f.queens.action.Validator;
import com.ten31f.queens.domain.Solution;
import com.ten31f.queens.engine.Engine;
import com.ten31f.queens.engine.Outcome;
import com.ten31f.queens.values.KNOWNSOLUTIONS;

public class GenerateData {

	static final Logger logger = Logger.getLogger(GenerateData.class);

	private static final String ERROR_MESSAGE_UNKONW_LIMIT = "Unknown fundamental limit for boardsize: %s";

	private long n = 0;
	private List<Solution> solutions = null;
	private Engine engine = null;

	public GenerateData(long n,Engine engine) {
		setN(n);
		setSolutions(new ArrayList<>());
		setEngine(engine);
	}

	public long getN() {
		return n;
	}

	private void setN(long n) {
		this.n = n;
	}

	public void run() {
		if (getN() > KNOWNSOLUTIONS.FUNDAMENTAL.length) {
			logger.error(String.format(ERROR_MESSAGE_UNKONW_LIMIT, getN()));
			return;
		}

		long limit = KNOWNSOLUTIONS.FUNDAMENTAL[(int) (getN() - 1)];

		while (getSolutions().size() < limit) {
			Solution solution = new Solution(getN());

			while (!Validator.isComplete(solution) && !getEngine().giveUp()) {

				int[] newPosition = getEngine().nextPosition();

				getEngine().digest(newPosition, addPosition(newPosition[0], newPosition[1], solution));

			}

			Permutator.permutate(solution);

			if (Validator.validate(solution)) {
				getSolutions().add(solution);
			}

		}
	}

	public Outcome addPosition(int x, int y, Solution solution) {

		if (solution.getPrimary()[y] != -1) {
			if (solution.getPrimary()[y] == x) {
				return Outcome.DUPLICATE;
			}

			return Outcome.ROW;
		}

		solution.getPrimary()[y] = x;

		return Outcome.CLEAR;

	}

	public List<Solution> getSolutions() {
		return solutions;
	}

	private void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	private Engine getEngine() {
		return engine;
	}

	private void setEngine(Engine engine) {
		this.engine = engine;
	}

}
