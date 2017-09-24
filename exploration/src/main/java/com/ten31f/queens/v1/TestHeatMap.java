package com.ten31f.queens.v1;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.ten31f.queens.v1.analysis.CollectHeatData;

public class TestHeatMap {

	//private static final String HOST = "1031f.com";
	private static final String HOST = "192.168.1.51";
	
	private static final String DATABASE = "queens";
	private static final int LIMIT = 8;

	public static void main(String[] args) {

		MongoClient mongo;
		try {
			mongo = new MongoClient(HOST, 27017);

			DB db = mongo.getDB(DATABASE);

			CollectHeatData collectHeatData = new CollectHeatData(db, LIMIT);
			
			collectHeatData.analizeData();
			collectHeatData.printHeatMap();

		} catch (UnknownHostException unknownHostException) {

			unknownHostException.printStackTrace();
		}
	}

}
