package com.ten31f.queens.v1.scripts;

import com.mongodb.DB;
import com.ten31f.queens.domain.Solution;
import com.ten31f.queens.v1.ai.Player;
import com.ten31f.queens.v1.concepts.Game;

public class CatchThemAll extends AbstractScript {

	private long target;
	private long solvedCount;

	public CatchThemAll(long n, DB db, Player player, String collection, long target) {
		super(n, db, player, collection);
		setTarget(target);
		setSolvedCount(0);
	}

	@Override
	public void run() {

		while (getSolvedCount() < getTarget()) {
			getPlayer().reset();

			Game game = new Game((int) getN());
			while (!game.isfull() && !getPlayer().giveUp()) {
				int[] newPosition = getPlayer().nextPosition();
				getPlayer().digest(newPosition, game.addPosition(newPosition[0], newPosition[1]));
			}

			game.solved();
			game.findMirrors();

			Solution solution = game.getSolution();

			if (recordGame(solution)) {
				setRecorded(getRecorded() + 1);
				if (solution.isSolved())
					setSolvedCount(getSolvedCount() + 1);
			} else {
				setRejected(getRejected() + 1);
			}
		}

		status();

	}

	public void status() {
		System.out.println("===========================================");
		System.out.println(getCollection());
		System.out.println("Recorded:\t" + getRecorded());
		System.out.println("Rejected:\t" + getRejected());
		System.out.println(getSolvedCount() + " / " + getTarget());
	}

	public void setTarget(long target) {
		this.target = target;
	}

	public long getTarget() {
		return target;
	}

	public void setSolvedCount(long solvedCount) {
		this.solvedCount = solvedCount;
	}

	public long getSolvedCount() {
		return solvedCount;
	}

}
