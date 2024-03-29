package com.ten31f.queens.v1;

import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.ten31f.queens.action.Permutator;
import com.ten31f.queens.action.Validator;
import com.ten31f.queens.domain.Solution;
import com.ten31f.queens.v1.ai.OnceBurned;
import com.ten31f.queens.v1.ai.Player;
import com.ten31f.queens.v1.concepts.Game;

public class MongoTest {

	private static final String HOST = "1031f.com";
	private static final String DATABASE = "queens";
	private static final int LIMIT = 8;

	public static void main(String[] args) {

		// Enable MongoDB logging in general
		System.setProperty("DEBUG.MONGO", "true");

		// Enable DB operation tracing
		System.setProperty("DB.TRACE", "true");

		MongoClient mongo = new MongoClient(HOST, 27017);

		DB db = (DB) mongo.getDatabase(DATABASE);

		DBCollection dbCollection = db.getCollection("games");

		Game game = new Game(LIMIT);

		Player player = new OnceBurned(LIMIT, new Random(System.nanoTime()));

		while (!Validator.isComplete(game.getSolution()) && !player.giveUp()) {

			int[] newPosition = player.nextPosition();

			player.digest(newPosition, game.addPosition(newPosition[0], newPosition[1]));

		}

		Solution solution = game.getSolution();
		Permutator.permutate(solution);

		Gson gson = new GsonBuilder().create();

		for (int[] permutation : solution.getPermutations()) {

			String jsonString = gson.toJson(permutation);

			DBObject arrayDBObject = (DBObject) JSON.parse(jsonString);

			BasicDBObject query = new BasicDBObject("permutations", arrayDBObject);

			DBCursor dbCursor = dbCollection.find(query);

			if (dbCursor.count() > 0) {
				return;
			}

		}

		String jsonString = gson.toJson(solution);

		DBObject dbObject = (DBObject) JSON.parse(jsonString);

		dbCollection.insert(dbObject);

		mongo.close();
	}
}
