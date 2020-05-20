package com.ten31f.queens.engine;

public interface Engine {

	int[] nextPosition();

	void digest(int[] position, Outcome outcome);

	boolean giveUp();

	public void reset();
	
	public String getName();

}