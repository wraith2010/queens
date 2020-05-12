package com.ten31f.queens.v1.scripts;

import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.ten31f.queens.domain.Solution;
import com.ten31f.queens.v1.ai.Player;
import com.ten31f.queens.v1.concepts.Game;

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

			Game game = new Game((int) getN());
			while (!game.isfull() && !getPlayer().giveUp()) {

				int[] newPosition = getPlayer().nextPosition();

				getPlayer().digest(newPosition, game.addPosition(newPosition[0], newPosition[1]));

			}

			game.findMirrors();

			Solution solution = game.getSolution();

			if (recordGame(solution)) {
				setRecorded(getRecorded() + 1);
			} else {
				setRejected(getRejected() + 1);
			}
		}
	}

}
