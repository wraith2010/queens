package com.ten31f.queens.v1;

import java.util.Random;

import com.ten31f.queens.action.Permutator;
import com.ten31f.queens.action.Validator;
import com.ten31f.queens.domain.Solution;
import com.ten31f.queens.v1.ai.Player;
import com.ten31f.queens.v1.ai.RollingSolution;
import com.ten31f.queens.v1.concepts.Game;

public class PlayTillWin {

	private static final int LIMIT = 8;

	public static void main(String[] args) {

		int gameCount = 0;

		Game game = null;

		// Player player = new OnceBurned(LIMIT, new Random(System.nanoTime()));
		Player player = new RollingSolution(LIMIT, new Random(System.nanoTime()));

		while (true) {

			gameCount++;

			player.reset();

			game = new Game(LIMIT);
			while (!Validator.isComplete(game.getSolution()) && !player.giveUp()) {

				int[] newPosition = player.nextPosition();

				player.digest(newPosition, game.addPosition(newPosition[0], newPosition[1]));

			}

			Solution solution = game.getSolution();
			Permutator.permutate(solution);

			if (Validator.validate(solution))
				break;

			System.out.println("Game:\t" + gameCount);

			Permutator.permutate(solution);

			System.out.println(solution);

		}

		Solution solution = game.getSolution();
		Permutator.permutate(solution);
		System.out.println(solution);

	}

}
