package com.ten31f.queens.v1.ai;

import com.ten31f.queens.v1.concepts.Outcome;
import com.ten31f.queens.v1.concepts.Position;

public interface Player {

	Position nextPosition();

	void digest(Position position, Outcome outcome);

	boolean giveUp();

	public void reset();
	
	public String getName();

}