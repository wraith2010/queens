package com.ten31f.queens.v1.scripts;

import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.ten31f.queens.v1.ai.Player;
import com.ten31f.queens.v1.concepts.Game;
import com.ten31f.queens.v1.concepts.Position;
import com.ten31f.queens.v1.domain.GameData;

public class OneThousandGames extends AbstractScript {

	public OneThousandGames(int n, DB db, Player player, String collection) {

		super(n, db, player, collection);

		setN(n);
		setDb(db);
		setGson(new GsonBuilder().create());
		setPlayer(player);
	}

	public void run() {

		while (getRecorded() < 1000) {

			getPlayer().reset();

			Game game = new Game(getN());
			while (!game.isfull() && !getPlayer().giveUp()) {

				Position newPosition = getPlayer().nextPosition();

				getPlayer().digest(newPosition, game.legalAdd(newPosition));

			}

			game.findMirrors();

			GameData gameData = game.getGameData();
			gameData.setSolved(game.solved());

			if (recordGame(gameData)) {
				setRecorded(getRecorded() + 1);
			} else {
				setRejected(getRejected() + 1);
			}
		}
	}

}
