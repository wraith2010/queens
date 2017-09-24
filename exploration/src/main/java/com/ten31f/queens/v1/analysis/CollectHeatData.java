package com.ten31f.queens.v1.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.ten31f.queens.v1.concepts.Position;
import com.ten31f.queens.v1.domain.GameData;
import com.ten31f.queens.v1.domain.PositionHeat;

public class CollectHeatData {

	private static final String COLLECTION_GAMES = "games";

	private DB db;
	private Gson gson;
	private int n;

	private Map<Position, PositionHeat> heatMap = null;
	private List<Integer> ordinalTotals = null;

	public CollectHeatData(DB db, int n) {
		setDb(db);
		setN(n);
		setGson(new GsonBuilder().create());
		init();
	}

	public void init() {
		setHeatMap(new HashMap<>());
		setOrdinalTotals(new ArrayList<>());
		for (int x = 0; x < getN(); x++) {
			for (int y = 0; y < getN(); y++) {
				getHeatMap().put(new Position(x, y), new PositionHeat(x, y, getN(), getOrdinalTotals()));
			}
			getOrdinalTotals().add(0);
		}

	}

	public void analizeData() {

		Gson gson = new GsonBuilder().create();

		DBCollection dbCollectionGames = getDb().getCollection(COLLECTION_GAMES);

		DBCursor dbCursor = dbCollectionGames.find();

		while (dbCursor.hasNext()) {

			DBObject dbObject = dbCursor.next();

			GameData gameData = gson.fromJson(dbObject.toString(), GameData.class);

			int ordinal = (int) (gameData.getN()) - gameData.getPermutations().get(0).size();

			for (List<Position> permution : gameData.getPermutations()) {
				for (Position position : permution) {
					PositionHeat positionHeat = getHeatMap().get(position);
					positionHeat.recordeHeat(ordinal);
					getOrdinalTotals().set(ordinal, getOrdinalTotals().get(ordinal) + 1);
				}
			}

		}

		for (int index = 0; index < getN(); index++) {
			getOrdinalTotals().set(index, getOrdinalTotals().get(index) / getHeatMap().size());
		}
	}

	public void printHeatMap() {

		StringBuilder stringBuilder = new StringBuilder();

		for (int y = 0; y < getN(); y++) {
			for (int x = 0; x < getN(); x++) {
				PositionHeat positionHeat = getHeatMap().get(new Position(x, y));
				stringBuilder.append('|');
				stringBuilder.append(positionHeat);
				stringBuilder.append('|');
			}
			stringBuilder.append('\n');
		}
		stringBuilder.append('\n');

		System.out.println(stringBuilder.toString());
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

	public Map<Position, PositionHeat> getHeatMap() {
		return heatMap;
	}

	public void setHeatMap(Map<Position, PositionHeat> heatMap) {
		this.heatMap = heatMap;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getN() {
		return n;
	}

	public List<Integer> getOrdinalTotals() {
		return ordinalTotals;
	}

	public void setOrdinalTotals(List<Integer> ordinalTotals) {
		this.ordinalTotals = ordinalTotals;
	}
}
