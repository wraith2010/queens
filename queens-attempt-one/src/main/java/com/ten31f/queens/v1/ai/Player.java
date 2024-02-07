package com.ten31f.queens.v1.ai;

import com.ten31f.queens.enums.Outcome;

public interface Player {

	int[] nextPosition();

	void digest(int[] position, Outcome outcome);

	boolean giveUp();

	public void reset();
	
	public String getName();

}