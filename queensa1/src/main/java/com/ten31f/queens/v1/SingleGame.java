package com.ten31f.queens.v1;

import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ten31f.queens.v1.ai.Player;
import com.ten31f.queens.v1.ai.RollingSolution;
import com.ten31f.queens.v1.concepts.Game;

/**
 * Queens test bed
 *
 */
public class SingleGame {
	private static final int LIMIT = 8;

	public static void main(String[] args) {

		// Player player = new OnceBurned(LIMIT, new Random(System.nanoTime()));
		Player player = new RollingSolution(LIMIT, new Random(System.nanoTime()));

		Game game = new Game(LIMIT);
		while (!game.isfull() && !player.giveUp()) {

			int[] newPosition = player.nextPosition();

			player.digest(newPosition, game.addPosition(newPosition[0], newPosition[1]));
		}

		game.findMirrors();

		Gson gson = new GsonBuilder().create();
		gson.toJson(game.getSolution(), System.out);

	}
}
