package com.ten31f.queens.v1.scripts;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.ten31f.queens.v1.ai.Player;
import com.ten31f.queens.v1.concepts.Position;
import com.ten31f.queens.v1.domain.GameData;

public abstract class AbstractScript {

	public static final String JSON_KEY_PERMUTATIONS = "permutations";

	private long rejected;
	private long recorded;
	private long n;

	private DB db;
	private Gson gson;

	private Player player;

	private String collection;

	public AbstractScript(long n, DB db, Player player, String collection) {
		setN(n);
		setDb(db);
		setGson(new GsonBuilder().create());
		setPlayer(player);
		setCollection(collection);
	}

	abstract public void run();

	protected boolean recordGame(GameData gameData) {

		DBCollection dbCollection = getDb().getCollection(getCollection());

		for (List<Position> permutation : gameData.getPermutations()) {

			String jsonString = getGson().toJson(permutation);

			DBObject arrayDBObject = (DBObject) JSON.parse(jsonString);

			BasicDBObject query = new BasicDBObject(JSON_KEY_PERMUTATIONS, arrayDBObject);

			if (dbCollection.count(query) > 0) {
				return false;
			}

		}

		String jsonString = getGson().toJson(gameData);

		DBObject dbObject = (DBObject) JSON.parse(jsonString);

		dbCollection.insert(dbObject);

		return true;

	}

	public long getRecorded() {
		return recorded;
	}

	public void setRecorded(long recorded) {
		this.recorded = recorded;
	}

	public long getRejected() {
		return rejected;
	}

	public void setRejected(long rejected) {
		this.rejected = rejected;
	}

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

	public DB getDb() {
		return db;
	}

	public void setDb(DB db) {
		this.db = db;
	}

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

}
