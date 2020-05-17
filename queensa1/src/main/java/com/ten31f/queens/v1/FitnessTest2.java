package com.ten31f.queens.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.ten31f.queens.v1.ai.OnceBurned;
import com.ten31f.queens.v1.ai.Player;
import com.ten31f.queens.v1.ai.RollingSolution;
import com.ten31f.queens.v1.scripts.CatchThemAll;

public class FitnessTest2 {

	private static long[] knownSolutions = { 1, 0, 0, 1, 2, 1, 6, 12, 46, 92, 341, 1787, 9233, 45752, 285053, 1846955,
			11977939, 83263591, 621012754 };

	private static final String HOST = "1031f.com";
	private static final String DATABASE = "queens";

	public static void main(String[] args) {

		Random random = new Random(System.nanoTime());

		for (int n = 1; n < 14; n++) {
			List<Player> players = new ArrayList<>();
			players.add(new OnceBurned(n, random));
			players.add(new RollingSolution(n, random));

			while (!players.isEmpty()) {
				Player player = players.remove(0);
				for (int iteration = 0; iteration < 10; iteration++) {
					String collectionName = player.getName() + '-' + n + '-' + iteration;
					test(n, knownSolutions[n - 1], collectionName, player);
				}
			}
		}

	}

	public static void test(long n, long target, String collectionName, Player player) {

		if (target < 1)
			return;

		MongoClient mongo = new MongoClient(HOST, 27017);

		DB db = mongo.getDB(DATABASE);

		CatchThemAll catchThemAll = new CatchThemAll(n, db, player, collectionName, target);

		catchThemAll.run();

	}

}
