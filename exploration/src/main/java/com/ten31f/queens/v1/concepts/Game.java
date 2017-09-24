package com.ten31f.queens.v1.concepts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.ten31f.queens.v1.domain.GameData;

public class Game {

	private GameData gameData;

	public Game(long n) {
		setGameData(new GameData());
		getGameData().setN(n);
	}

	public void setGameData(GameData gameData) {
		this.gameData = gameData;
	}

	public GameData getGameData() {
		return gameData;
	}

	public void addPosition(Position newPosition) {
		if (getGameData().getPermutations() == null)
			getGameData().setPermutations(new ArrayList<>());

		if (getGameData().getPermutations().isEmpty())
			getGameData().getPermutations().add(new ArrayList<>());

		for (Position position : getGameData().getPermutations().get(0)) {
			if (position.equals(newPosition))
				return;
		}

		getGameData().getPermutations().get(0).add(newPosition);
	}

	public Outcome legalAdd(Position newPosition) {

		if (getGameData().getPermutations() == null)
			getGameData().setPermutations(new ArrayList<>());

		if (getGameData().getPermutations().isEmpty())
			getGameData().getPermutations().add(new ArrayList<>());

		if (getGameData().getPermutations().get(0).isEmpty()) {

			getGameData().getPermutations().get(0).add(newPosition);

			return Outcome.CLEAR;
		}

		for (Position position : getGameData().getPermutations().get(0)) {
			if (position.equals(newPosition))
				return Outcome.DUPLICATE;

			Outcome outcome = newPosition.canAttack(position);
			if (!Outcome.CLEAR.equals(outcome))
				return outcome;
		}

		getGameData().getPermutations().get(0).add(newPosition);

		return Outcome.CLEAR;
	}

	public boolean isfull() {

		if (getGameData().getPermutations() == null)
			return false;

		if (getGameData().getPermutations().get(0) == null)
			return false;

		return !(getGameData().getPermutations().get(0).size() < getGameData().getN());
	}

	public boolean solved() {

		if (getGameData().getSolved() != null)
			return getGameData().getSolved();

		if (getGameData().getPermutations().get(0).size() < getGameData().getN())
			return false;

		Deque<Position> positionsToConsider = new LinkedList<>(getGameData().getPermutations().get(0));

		while (!positionsToConsider.isEmpty()) {

			Position positionA = positionsToConsider.pop();

			positionsToConsider.addLast(positionA);

			while (!positionsToConsider.peek().equals(positionA)) {

				if (!Outcome.CLEAR.equals(positionsToConsider.peek().canAttack(positionA)))
					return false;

				positionsToConsider.addLast(positionsToConsider.pop());
			}

			positionsToConsider.pop();
		}

		getGameData().setSolved(true);

		return true;
	}

	public void printGame(boolean printboard) {

		System.out.println("Solved:\t" + solved());

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("\n");

		for (List<Position> mirror : getGameData().getPermutations()) {
			for (Position position : mirror) {
				stringBuilder.append(position);
			}

			stringBuilder.append("\n");

			if (printboard) {
				stringBuilder.append(printBoard(mirror));
				stringBuilder.append("\n");
			}
		}

		System.out.println(stringBuilder.toString());
	}

	private String printBoard(List<Position> positions) {

		StringBuilder stringBuilder = new StringBuilder();

		Deque<Position> positionsToPrint = new LinkedList<>(positions);

		Position positionNextToPrint = positionsToPrint.pop();

		for (int y = 0; y < getGameData().getN(); y++) {
			for (int x = 0; x < getGameData().getN(); x++) {
				if (positionNextToPrint != null && x == positionNextToPrint.getX() && y == positionNextToPrint.getY()) {
					stringBuilder.append("|Q|");
					positionNextToPrint = positionsToPrint.poll();
				} else {
					stringBuilder.append("| |");
				}
			}
			stringBuilder.append("\n");
		}

		return stringBuilder.toString();
	}

	private boolean addMirror(List<Position> mirror) {

		Collections.sort(mirror);

		for (List<Position> permutation : getGameData().getPermutations()) {

			if (identical(mirror, permutation))
				return false;
		}

		getGameData().getPermutations().add(mirror);

		return true;
	}

	private boolean identical(List<Position> permutation1, List<Position> permutation2) {

		for (int index = 0; index < permutation1.size(); index++) {
			if (!permutation1.get(index).equals(permutation2.get(index)))
				return false;
		}

		return true;
	}

	public void findMirrors() {

		Collections.sort(getGameData().getPermutations().get(0));

		addMirror(findhorizontalMirror(getGameData().getPermutations().get(0)));
		addMirror(findverticalMirror(getGameData().getPermutations().get(0)));
		addMirror(findverticalMirror(findhorizontalMirror(getGameData().getPermutations().get(0))));
		addMirror(findNinteyDegreeMirror(getGameData().getPermutations().get(0)));
		addMirror(findhorizontalMirror(findNinteyDegreeMirror(getGameData().getPermutations().get(0))));
		addMirror(findverticalMirror(findNinteyDegreeMirror(getGameData().getPermutations().get(0))));
		addMirror(findverticalMirror(
				findhorizontalMirror(findNinteyDegreeMirror(getGameData().getPermutations().get(0)))));

	}

	private List<Position> findhorizontalMirror(List<Position> positions) {

		List<Position> mirror = new ArrayList<>();

		for (Position position : positions) {
			mirror.add(new Position((int)(getGameData().getN() - 1) - position.getX(), position.getY()));
		}

		return mirror;
	}

	private List<Position> findverticalMirror(List<Position> positions) {

		List<Position> mirror = new ArrayList<>();

		for (Position position : positions) {
			mirror.add(new Position((int)position.getX(), (int) ((getGameData().getN() - 1) - position.getY())));
		}

		return mirror;
	}

	private List<Position> findNinteyDegreeMirror(List<Position> positions) {

		List<Position> mirror = new ArrayList<>();

		for (Position position : positions) {
			mirror.add(new Position(position.getY(), position.getX()));
		}

		return mirror;

	}
}
