package com.ten31f.queens.v1;

import java.util.Random;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.ten31f.queens.v1.ai.Player;
import com.ten31f.queens.v1.ai.RollingSolution;
import com.ten31f.queens.v1.scripts.CatchThemAll;

public class FitnessTest1 {

	private static long[] knownSolutions = { 1, 2, 1, 6, 12, 46, 92, 341, 1787, 9233, 45752, 285053, 1846955, 11977939,
			83263591, 621012754 };

	// private static final String HOST = "192.168.1.51";
	private static final String HOST = "1031f.com";
	private static final String DATABASE = "queens";

	public static void main(String[] args) {

		for (int n = 1; n < 14; n++) {
			test(n, knownSolutions[n - 1]);
		}

	}

	public static void test(int n, long target) {

		if (target < 1)
			return;

		MongoClient mongo = new MongoClient(HOST, 27017);

		DB db = (DB) mongo.getDatabase(DATABASE);

		// Player player = new OnceBurned(n, new Random(System.nanoTime()));
		Player player = new RollingSolution(n, new Random(System.nanoTime()));

		// String collectionName = "OnceBurned" + n;
		String collectionName = "RollingSolution" + n;

		CatchThemAll catchThemAll = new CatchThemAll(n, db, player, collectionName, target);

		catchThemAll.run();

		mongo.close();
		
	}

}
