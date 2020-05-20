package com.ten31f.queens.repo;

import java.util.Arrays;

import org.bson.BsonArray;
import org.bson.BsonInt32;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.ten31f.queens.domain.Solution;

public class SolutionRepo {

	public static final int DEFAULT_MONGO_PORT = 27017;

	private static final String JSON_KEY_N = "n";
	private static final String JSON_KEY_PERMUTAIONS = "permutations";

	private MongoClient mongoClient = null;

	private String databaseName = null;

	public static enum SaveOutcome {
		UNIQUE, DUPLICATE
	}

	public SolutionRepo(String databaseName, String username, String password, String databseURL, int port) {

		MongoCredential credential = MongoCredential.createCredential(username, databaseName, password.toCharArray());
		setMongoClient(new MongoClient(new ServerAddress(databseURL, 27017), Arrays.asList(credential)));
		setDatabaseName(databaseName);
	}

	public synchronized SaveOutcome save(Solution solution) {

		MongoCollection<Document> mongoCollection = getMongoClient().getDatabase(getDatabaseName())
				.getCollection(caculateColletionName(solution));

		Document document = new Document(JSON_KEY_N, solution.getN());

		BsonArray bsonArrayPermutions = new BsonArray();

		for (int[] permutation : solution.getPermutations()) {
			bsonArrayPermutions.add(permutaiontoBsonArray(permutation));
		}

		document.append(JSON_KEY_PERMUTAIONS, bsonArrayPermutions);

		if (!isUnique(solution)) {
			return SaveOutcome.DUPLICATE;
		}

		mongoCollection.insertOne(document);

		return SaveOutcome.UNIQUE;
	}

	public boolean isUnique(Solution solution) {

		MongoCollection<Document> mongoCollection = getMongoClient().getDatabase(getDatabaseName())
				.getCollection(caculateColletionName(solution));

		for (int[] permutation : solution.getPermutations()) {

			Document document = new Document(JSON_KEY_PERMUTAIONS, permutaiontoBsonArray(permutation));

			if (mongoCollection.count(document) > 0) {
				return false;
			}

		}

		return true;
	}

	private BsonArray permutaiontoBsonArray(int[] permutation) {
		BsonArray bsonArray = new BsonArray();
		for (int index = 0; index < permutation.length; index++) {
			bsonArray.add(new BsonInt32(permutation[index]));
		}

		return bsonArray;
	}

	private String caculateColletionName(Solution solution) {
		return solution.getN() + "queens";
	}

	private void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	private MongoClient getMongoClient() {
		return mongoClient;
	}

	private void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	private String getDatabaseName() {
		return databaseName;
	}
}
