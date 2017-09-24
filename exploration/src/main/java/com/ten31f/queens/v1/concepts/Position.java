package com.ten31f.queens.v1.concepts;

public class Position implements Comparable<Position> {

	private int x;
	private int y;

	public Position(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Outcome canAttack(Position positionb) {

		if (this.equals(positionb))
			return Outcome.DUPLICATE;

		if (getX() == positionb.getX())
			return Outcome.FILE;
		if (getY() == positionb.getY())
			return Outcome.ROW;

		if (Math.abs(getX() - positionb.getX()) == Math.abs(getY() - positionb.getY()))
			return Outcome.DIAGONAL;

		return Outcome.CLEAR;
	}

	@Override
	public boolean equals(Object o) {
		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		Position positionb = (Position) o;

		if (positionb == null)
			return false;

		return getX() == positionb.getX() && getY() == positionb.getY();
	}

	@Override
	public String toString() {
		return '(' + Integer.toString(getX()) + ',' + Integer.toString(getY()) + ')';
	}

	@Override
	public int hashCode() {

		return Integer.parseInt(
				Integer.toString(getX()) + Integer.toString(getY()) + Integer.toBinaryString(getX() * getY()));
	}

	@Override
	public int compareTo(Position other) {

		if (getY() < other.getY())
			return -1;

		if (getY() > other.getY())
			return 1;

		if (getX() < other.getX())
			return -1;

		if (getX() > other.getX())
			return 1;

		return 0;
	}
}
