package com.ten31f.queens.v1;

import java.net.UnknownHostException;
import java.util.List;
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
import com.ten31f.queens.v1.ai.OnceBurned;
import com.ten31f.queens.v1.ai.Player;
import com.ten31f.queens.v1.concepts.Game;
import com.ten31f.queens.v1.concepts.Position;
import com.ten31f.queens.v1.domain.GameData;

public class MongoTest {

	private static final String HOST = "1031f.com";
	private static final String DATABASE = "queens";
	private static final int LIMIT = 8;

	public static void main(String[] args) {

		// Enable MongoDB logging in general
		System.setProperty("DEBUG.MONGO", "true");

		// Enable DB operation tracing
		System.setProperty("DB.TRACE", "true");

		MongoClient mongo;
		try {
			mongo = new MongoClient(HOST, 27017);

			DB db = mongo.getDB(DATABASE);

			DBCollection dbCollection = db.getCollection("games");

			Game game = new Game(LIMIT);

			
			
			Player player = new OnceBurned(LIMIT, new Random(System.nanoTime()));

			
			while (!game.isfull() && !player.giveUp()) {

				Position newPosition = player.nextPosition();

				player.digest(newPosition, game.legalAdd(newPosition));

			}

			game.findMirrors();

			GameData gameData = game.getGameData();
			gameData.setSolved(game.solved());

			Gson gson = new GsonBuilder().create();

			boolean unique = true;

			for (List<Position> permutation : gameData.getPermutations()) {

				String jsonString = gson.toJson(permutation);

				DBObject arrayDBObject = (DBObject) JSON.parse(jsonString);

				BasicDBObject query = new BasicDBObject("permutations", arrayDBObject);

				DBCursor dbCursor = dbCollection.find(query);

				if (dbCursor.count() > 0) {
					unique = false;
					return;
				}

			}

			if (unique) {
				String jsonString = gson.toJson(gameData);

				DBObject dbObject = (DBObject) JSON.parse(jsonString);

				dbCollection.insert(dbObject);

			}

		} catch (UnknownHostException unknownHostException) {

			unknownHostException.printStackTrace();
		}

	}
}
