package com.ten31f.queens.v1;

import java.util.Random;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.ten31f.queens.v1.ai.OnceBurned;
import com.ten31f.queens.v1.ai.Player;
import com.ten31f.queens.v1.scripts.OneThousandGames;

public class MakeData {

	// private static final String HOST = "1031f.com";
	private static final String HOST = "192.168.1.51";
	private static final String DATABASE = "queens";
	private static final int N = 8;

	public static void main(String[] args) {

		MongoClient mongo;
		mongo = new MongoClient(HOST, 27017);

		DB db = (DB) mongo.getDatabase(DATABASE);

		Player player = new OnceBurned(N, new Random(System.nanoTime()));

		String collection = N + "OneThousandGames";

		OneThousandGames oneThousandGames = new OneThousandGames(N, db, player, collection);

		oneThousandGames.run();

		System.out.println("Rejected:\t" + oneThousandGames.getRejected());
		System.out.println("Recorded:\t" + oneThousandGames.getRecorded());

		mongo.close();

	}

}
