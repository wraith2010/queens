package com.ten31f.queens.task;

import java.util.logging.Logger;

import java.util.logging.Level;

import com.ten31f.queens.action.Permutator;
import com.ten31f.queens.action.Validator;
import com.ten31f.queens.domain.Solution;
import com.ten31f.queens.engine.Engine;
import com.ten31f.queens.engine.Outcome;
import com.ten31f.queens.repo.SolutionRepo;
import com.ten31f.queens.repo.SolutionRepo.SaveOutcome;
import com.ten31f.queens.values.KNOWNSOLUTIONS;

public class GenerateData implements Runnable {

	static final Logger logger = Logger.getLogger(GenerateData.class.getName());

	private static final String ERROR_MESSAGE_UNKONW_LIMIT = "Unknown fundamental limit for boardsize: %s";

	private long n = 0;
	public static long solutionCount = 0;
	private Engine engine = null;

	private SolutionRepo solutionRepo = null;

	public GenerateData(long n, Engine engine, SolutionRepo solutionRepo) {
		setN(n);
		setEngine(engine);
		setSolutionRepo(solutionRepo);
	}

	public long getN() {
		return n;
	}

	private void setN(long n) {
		this.n = n;
	}

	@Override
	public void run() {
		if (getN() > KNOWNSOLUTIONS.FUNDAMENTAL.length) {
			logger.log(Level.SEVERE, ERROR_MESSAGE_UNKONW_LIMIT);
			return;
		}

		long limit = KNOWNSOLUTIONS.FUNDAMENTAL[(int) (getN() - 1)];

		while (solutionCount < limit) {

			getEngine().reset();

			Solution solution = new Solution(getN());

			while (!Validator.isComplete(solution) && !getEngine().giveUp()) {
				int[] newPosition = getEngine().nextPosition();
				getEngine().digest(newPosition, addPosition(newPosition[0], newPosition[1], solution));
			}

			if (Validator.validate(solution)) {
				Permutator.permutate(solution);
				if (SaveOutcome.UNIQUE == getSolutionRepo().save(solution)) {
					logger.log(Level.INFO, solution.toString());
					solutionCount++;
					System.out.println(solutionCount + "/" + limit);
					System.out.println(solution);
				}
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

	private Engine getEngine() {
		return engine;
	}

	private void setEngine(Engine engine) {
		this.engine = engine;
	}

	private SolutionRepo getSolutionRepo() {
		return solutionRepo;
	}

	private void setSolutionRepo(SolutionRepo solutionRepo) {
		this.solutionRepo = solutionRepo;
	}
}
