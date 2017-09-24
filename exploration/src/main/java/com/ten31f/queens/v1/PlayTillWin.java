package com.ten31f.queens.v1;

import java.util.Random;

import com.ten31f.queens.v1.ai.Player;
import com.ten31f.queens.v1.ai.RollingSolution;
import com.ten31f.queens.v1.concepts.Game;
import com.ten31f.queens.v1.concepts.Position;

public class PlayTillWin {

	private static final int LIMIT = 8;

	public static void main(String[] args) {

		int gameCount = 0;

		Game game = null;

		//Player player = new OnceBurned(LIMIT, new Random(System.nanoTime()));
		Player player = new RollingSolution(LIMIT, new Random(System.nanoTime()));
		
		while (true) {

			gameCount++;

			player.reset();

			game = new Game(LIMIT);
			while (!game.isfull() && !player.giveUp()) {

				Position newPosition = player.nextPosition();

				player.digest(newPosition, game.legalAdd(newPosition));

			}

			if (game.solved())
				break;

			System.out.println("Game:\t" + gameCount);

			game.findMirrors();

			game.printGame(false);

		}

		System.out.println("Game:\t" + gameCount);

		game.findMirrors();

		game.printGame(false);

	}

}
